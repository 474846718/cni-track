package com.cni.converter.support;


/**
 * TrackOrder的适配器接口
 */
public interface OrderBillAdpt {

    String getNumber();

    String getOrigin();

    String getDestination();

    Long getEstimatedDate();

    String getCustomer();

    String getConsignee();

    String getConsigneeTelNo();

    String getReceiveBy();

    String getTailCompany();

    String getReferenceNo();

    String getPackageType();

    Double getWeight();

    Integer getPincode();

    String getFlow();

    String getFlowDirection();

    Integer getDispatchCount();

}