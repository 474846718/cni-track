package com.cni.statemap;

import com.cni.converter.BluedartConverter;
import com.cni.dao.StateMappingDao;
import com.cni.dao.entity.CompStateMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 这个类初始化时尝试链接数据库
 */
public class DefaultHolder{

    //最好替换成枚举类型
    private static final String[] COMPANY_NAME=new String[]{ "DELHIVERY","BLUE_DART","ECOM_EXPRESS","INDIA_POST","GATI"};
    private StateMappingDao stateMappingDao;

    private Map<String,List<CompStateMapping>> compMap=new HashMap<>();
    private static final Log log = LogFactory.getLog(BluedartConverter.class);
    private int retry=3;
    private boolean isLastConfigOk;

    public DefaultHolder(StateMappingDao stateMappingDao){
        this.stateMappingDao=stateMappingDao;
    }

    /**
     * 重新从数据库加载配置
     * @return
     */
    public boolean reload(){
        return isLastConfigOk=initFromDatabaseRetry();
    }



    private boolean initFromDatabase(){
        try {
            for (String compName : COMPANY_NAME){
                List<CompStateMapping> stateMappingDocuments=stateMappingDao.findByCompName(compName);
                compMap.put(compName,stateMappingDocuments);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean initFromDatabaseRetry(){
        for (int i = 0; i <retry ; i++)
            if(initFromDatabase()) return true;
        return false;
    }

    /**
     * 设置尝试读取次数
     * @param retry
     */
    public void setRetry(int retry) {
        this.retry = retry;
    }

    /**
     * @param currState
     * @param compName
     * @return
     */
    public List<String> getMappingStrings(String currState, String compName){
        List<CompStateMapping> stateMap=compMap.get(compName);
        //常量匹配
        List<CompStateMapping> constMatched=stateMap.stream()
                .filter(doc->doc.getConstStates().contains(currState))
                .collect(Collectors.toList());
        //正则匹配
        List<CompStateMapping> regexMatched=stateMap.stream()
                .filter(doc->!doc.getRegex().isEmpty())   //过滤空的
                .filter(doc->doc.getRegex().stream().anyMatch(currState::matches)) //过滤匹配的
                .collect(Collectors.toList());
        constMatched.addAll(regexMatched);

        List<String> list= new ArrayList<>(constMatched.size());
        for (CompStateMapping document:constMatched)
            list.add(document.getMyState());
        return list;
    }


    public boolean init() {
        return isLastConfigOk= initFromDatabaseRetry();
    }

    public boolean isLastConfigOk() {
        return isLastConfigOk;
    }

}
