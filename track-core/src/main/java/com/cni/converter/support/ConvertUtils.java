package com.cni.converter.support;

import com.cni.dao.entity.OrderBill;

/**
 * 封装转换过程中公用的方法
 */
public final class ConvertUtils {

    public final static String IGNORE = "Ignore";

    /**
     * 通过使用是适配器创建运单信息文档对象
     *
     * @param adpt
     * @return
     */
    public static OrderBill createOrderBill(OrderBillAdpt adpt) {
        OrderBill document = new OrderBill();
        document.setFlowDirection(adpt.getFlowDirection());
        document.setFlow(adpt.getFlow());
        document.setDispatchCount(adpt.getDispatchCount());
        document.setPincode(adpt.getPincode());
        document.setOrigin(adpt.getOrigin());
        document.setReferenceNo(adpt.getReferenceNo());
        document.setReceiveBy(adpt.getReceiveBy());
        document.setNumber(adpt.getNumber());
        document.setReferenceNo(adpt.getReferenceNo());
        document.setWeight(adpt.getWeight());
        document.setConsigneeTelNo(adpt.getConsigneeTelNo());
        document.setCustomer(adpt.getCustomer());
        document.setTailCompany(adpt.getTailCompany());
        document.setConsignee(adpt.getConsignee());
        document.setDestination(adpt.getDestination());
        document.setEstimatedDate(adpt.getEstimatedDate());
        document.setPackageType(adpt.getPackageType());
        return document;
    }

    /**
     * 通过适配器创建ScanBean
     *
     * @param adpt
     * @return
     */
    public static OrderBill.InfoNode createInfoNode(InfoNodeAdpt adpt) {
        OrderBill.InfoNode infoNode = new OrderBill.InfoNode();
        infoNode.setPlace(adpt.getPlace());
        infoNode.setDate(adpt.getDate());
        infoNode.setStatus(adpt.getStatus());
        infoNode.setInfo(adpt.getInfo());
        return infoNode;
    }


}
