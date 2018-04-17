package com.cni.service;

import com.alibaba.fastjson.JSON;
import com.cni.dao.entity.Waybill;
import com.cni.statemap.MapRow;
import com.cni.statemap.neoman.NeomanConfigHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MappingHolderService {

    private final NeomanConfigHolder holder;
    private final RedisTemplate<String, Waybill> redisTemplate;

    @Autowired
    public MappingHolderService(NeomanConfigHolder holder,
                                RedisTemplate<String, Waybill> redisTemplate) {
        this.holder = holder;
        this.redisTemplate = redisTemplate;

    }

    /**
     * 重新远程读取配置
     * 并且全部替换
     *
     * @return 成功或失败
     */
    public String reloadHolder() {
        try {
            holder.init();
            //TODO 添加清空redis的操作不生效
            redisTemplate.delete(redisTemplate.keys("*"));
            return JSON.toJSONString(holder.getCurrMapRows());
        } catch (Exception e) {
            System.err.println("从网络获取配置失败");
            return Boolean.toString(false);
        }
    }

    public void reloadHolder(List<MapRow> config) {
        holder.reload(config);
    }

    public String checkConf() {
        return JSON.toJSONString(holder.getCurrMapRows());
    }

    public void addConfig(List<MapRow> config) {
        holder.addMapRow(config);
    }

    public void removeConfig(List<MapRow> config) {
        holder.removeMapRows(config);
    }

    public void removeConfig(long statusMatchingId) {
        holder.removeMapRow(statusMatchingId);
    }
}
