package com.cni.dao.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "ArchiveWaybill")
public class ArchiveWaybill extends Waybill implements Serializable {
    //归档时间
    private LocalDateTime archiveTime;

    public LocalDateTime getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(LocalDateTime archiveTime) {
        this.archiveTime = archiveTime;
    }
}
