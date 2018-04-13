package com.cni.httptrack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 负责持有自拍送的单号
 * 提供给OrderTracker后将从钮门系统追踪运单信息
 * 而不是第三方渠道
 */

public class SelfDispatchNumHolder{
    private static final Logger log = LoggerFactory.getLogger(SelfDispatchNumHolder.class);
    private static final String fileName = "selfdispatch.txt";
    private static final String windowspath = "d:/";
    private static final String linuxpath = "/root/";
    private Set<String> orderNums;
    private File file;

    public void setOrderNums(Set<String> orderNums) {
        synchronized (this) {
            this.orderNums = orderNums;
        }
    }

    public Set<String> getOrderNums() {
        synchronized (this) {
            return orderNums;
        }
    }

    public boolean has(String num) {
        return getOrderNums().contains(num);
    }

    public void reset(Set<String> resetNums) {
        setOrderNums(Collections.synchronizedSet(resetNums));
        writeToFile(getOrderNums());
    }

    public Set<String> getAll() {
        return getOrderNums();
    }


    private void writeToFile(Set<String> resetNums) {
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(file,false));
            for (String num : resetNums) {
                writer.write(num);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            log.error("写入失败！", e);
        }
    }

    @PostConstruct
    public void postConstruct() {
        try {
            String path = System.getProperty("os.name").equals("Windows 10") ? windowspath : linuxpath;
            Resource resource = new FileSystemResource(path + fileName);
            file=resource.getFile();
            if (!file.exists())
                Assert.isTrue(resource.getFile().createNewFile(),"文件创建失败");
            Assert.notNull(file, "文件对象引用不能为空！");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            setOrderNums(reader.lines().collect(Collectors.toSet()));
            reader.close();
        } catch (IOException e) {
            log.error("读取自派送单号文件出错", e);
        }
    }
}
