package com.cni.statemap;


/**
 * 映射结果
 * 包含映射后的状态
 * 和要修改替换的映射前字符串
 */
public class MapResult {
    private String status;
    private String changedInfo;
    public MapResult(){}

    public MapResult(String status, String changedInfo) {
        this.status = status;
        this.changedInfo = changedInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChangedInfo() {
        return changedInfo;
    }

    public void setChangedInfo(String changedInfo) {
        this.changedInfo = changedInfo;
    }
}
