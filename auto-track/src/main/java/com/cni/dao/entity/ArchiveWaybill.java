package com.cni.dao.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection ="ArchiveWaybill" )
public class ArchiveWaybill extends Waybill implements Serializable {

    @Id
    private ObjectId id;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
