package com.cni.dao.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CompleteWaybill")
public class CompleteWaybill extends Waybill {
}
