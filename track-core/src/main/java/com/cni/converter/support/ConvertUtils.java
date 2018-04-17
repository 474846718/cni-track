package com.cni.converter.support;

import com.cni.dao.entity.Waybill;

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
    public static Waybill createOrderBill(WaybillAdpt adpt) {
        Waybill document = new Waybill();
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
    public static Waybill.SavePoint createInfoNode(SavePointAdpt adpt) {
        Waybill.SavePoint savePoint = new Waybill.SavePoint();
        savePoint.setPlace(adpt.getPlace());
        savePoint.setDate(adpt.getDate());
        savePoint.setStatus(adpt.getStatus());
        savePoint.setInfo(adpt.getInfo());
        return savePoint;
    }


}
