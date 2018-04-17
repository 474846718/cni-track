package com.cni.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 运单信息实体类
 * 不要随便修改字段 否则反序列化失败
 */

public class Waybill implements Serializable{
    public static final String FLOW_FORWARD = "FORWARD";
    public static final String FLOW_REVERSE = "REVERSE";
    public static final String FLOW_DIRECTION_ONWARD = "ONWARD";
    public static final String FLOW_DIRECTION_RETURN = "RETURN";

    public static final List<String> COMPLETE_STATUS=Arrays.asList("Delivered","Delivered Return","Lost");
    private static final List<String> EXCEPTIONS = Arrays.asList("Refuse", "Payment",
            "Address/Contact Issue", "Future Delivery", "Self Collect", "Delay", "Undelivered");



    /**
     * number : 1521511225280
     * origin : SZ
     * destination : XINDELI
     * estimatedDate : null
     * customer : ALI
     * consignee : LAL
     * consigneeTelNo : 4312623
     * receiveBy : PRACHIEE
     * headCompany : CNI
     * tailCompany : DELHIVERY
     * referenceNo : 15215165225280
     * packageType : PREPAID
     * weight : 0.6
     * flow : FORWARD
     * flowDirection : ONWARD
     * dispatchCount : 3
     * exception : null
     * exceptionDetail : null
     * savePoints : [{"place":"","date":1512030005816,"status":"Shipment Created","info":"Consignment Manifested"},{"place":"International Fulfillment Center","date":1512088740000,"info":"Check In Scan","status":"Check In Scan"},{"place":"International Fulfillment Center","date":1512092580000,"info":"Package Dispatched","status":"Outward Scan"},{"place":"Mumbai_Chndivli_PC (Maharashtra)","date":1512601765000,"info":"Shipment Picked Up from Client Location","status":"Connected to Last Mile Courier"},{"place":"Mumbai_Chndivli_PC (Maharashtra)","date":1512611198585,"info":"Shipment Recieved at Origin Center","status":"In Transit"},{"place":"Mumbai Hub (Maharashtra)","date":1512629878757,"info":"Consignment received","status":"In Transit"},{"place":"Mumbai Hub (Maharashtra)","date":1512683097957,"info":"Consignment dispatched from origin city","status":"In Transit"},{"place":"Ranchi_Hub (Jharkhand)","date":1512730292565,"info":"Consignment received at destination city","status":"Reached Destination City"},{"place":"Ranchi_2 (Jharkhand)","date":1512782328126,"info":"Received at destination city","status":"Reached Destination City"},{"place":"Ranchi_2 (Jharkhand)","date":1512784747668,"info":"Out for delivery","status":"Out For Delivery"},{"place":"Ranchi_2 (Jharkhand)","date":1512796357002,"info":"Bad/Incomplete Address","status":"Address/Contact Issue"},{"place":"Ranchi_2 (Jharkhand)","date":1512964634264,"info":"Out for delivery","status":"Out For Delivery"},{"place":"Ranchi_2 (Jharkhand)","date":1512978104002,"info":"Bad/Incomplete Address","status":"Address/Contact Issue"},{"place":"Ranchi_2 (Jharkhand)","date":1513043593714,"info":"Out for delivery","status":"Out For Delivery"},{"place":"Ranchi_2 (Jharkhand)","date":1513070409002,"info":"Customer Cancelled the order","status":"Refuse"},{"place":"Ranchi_2 (Jharkhand)","date":1513177654259,"info":"No client instructions to Reattempt","status":"Returned"},{"place":"Ranchi_Hub (Jharkhand)","date":1513227511175,"info":"Consignment received","status":"Returned In Transit"},{"place":"Ranchi_Hub (Jharkhand)","date":1513262994775,"info":"Consignment dispatched from destination city","status":"Returned In Transit"},{"place":"Gurgaon_Bilaspur_HB (Haryana)","date":1513400868642,"info":"Consignment received","status":"Returned In Transit"},{"place":"Delhi_Gateway_HB (Delhi)","date":1513414668988,"info":"Consignment received","status":"Returned In Transit"},{"place":"Gurgaon_Bilaspur_HB (Haryana)","date":1513456295829,"info":"Consignment received","status":"Returned In Transit"},{"place":"Bhiwandi_Mankoli_HB (Maharashtra)","date":1513580846207,"info":"Consignment received","status":"Returned In Transit"},{"place":"Mumbai Hub (Maharashtra)","date":1513599615710,"info":"Consignment received at return city","status":"Returned In Transit"},{"place":"BOM_Sakinaka_RP (Maharashtra)","date":1513641663856,"info":"Consignment received at return city","status":"Returned In Transit"},{"place":"BOM_Sakinaka_RP (Maharashtra)","date":1513650718934,"info":"Dispatched for RTO","status":"Returned In Transit"},{"place":"BOM_Sakinaka_RP (Maharashtra)","date":1513684227611,"info":"RETURN Accepted","status":"Returned Delivered"}]
     */

    @Id
    private String number;
    private String origin;
    private String destination;
    private Long estimatedDate;
    private String customer;
    private String consignee;
    @JsonIgnore
    private String consigneeTelNo;
    private String receiveBy;
    private String headCompany;
    private String tailCompany;
    private String referenceNo;
    private String packageType;
    private Double weight;
    private Integer pincode;
    private String flow;
    private String flowDirection;
    private Integer dispatchCount;

    private boolean exception;
    private String exceptionDetail;
    private Long latestDate;
    private String latestInfo;
    private String latestPlace;
    private String latestStatus;
    private List<SavePoint> savePoints = new ArrayList<>();


    public static Waybill error(String number) {
        Waybill body = new Waybill();
        body.setNumber(number);
        return body;
    }

    /**
     * 拼接另一个单号的追踪信息列表
     *
     * @param body 被拼接的单号
     */
    public void joinInfoNode(Waybill body) {
        if (!ObjectUtils.isEmpty(body) && !CollectionUtils.isEmpty(body.getSavePoints())) {
            savePoints.addAll(body.getSavePoints());
            setHeadCompany("CNI");
        }
        savePoints = savePoints.stream()
                // 选择日期非空的
                .filter(i -> !ObjectUtils.isEmpty(i.getDate()))
                // 选择信息或地点非空
                .filter(i -> (!ObjectUtils.isEmpty(i.getInfo()) || !ObjectUtils.isEmpty(i.getPlace())))
                //按照日期降序
                .sorted(Comparator.comparingLong(SavePoint::getDate).reversed())
                .collect(Collectors.toList());
        // 去除入库节点前的地点信息
        int index = findCheckInScanIdx(savePoints);
        if (index > -1)
            savePoints.stream()
                    .skip(index + 1)
                    .forEach(scan -> scan.setPlace(""));

        SavePoint latest = savePoints.get(0);
        if (EXCEPTIONS.contains(latest.getStatus())) {
            exception = true;
            exceptionDetail = latest.getInfo();
        }
        latestDate=latest.getDate();
        latestInfo=latest.getInfo();
        latestPlace=latest.getPlace();
        latestStatus=latest.getStatus();
    }

    /**
     * 在list中获取入库节点的索引位置
     * 倒序查找
     *
     * @return 返回索引位置找不到返回-1
     */
    private int findCheckInScanIdx(List<SavePoint> scans) {
        for (int i = scans.size() - 1; i > -1; i--) {
            String status = scans.get(i).status;
            if (!StringUtils.isEmpty(status) && status.equalsIgnoreCase("Check In Scan"))
                return i;
        }
        return -1;
    }


    @Override
    public String toString() {
        return "TrackOrder{" +
                "number='" + number + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", estimatedDate=" + estimatedDate +
                ", customer='" + customer + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneeTelNo='" + consigneeTelNo + '\'' +
                ", receiveBy='" + receiveBy + '\'' +
                ", headCompany='" + headCompany + '\'' +
                ", tailCompany='" + tailCompany + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                ", packageType='" + packageType + '\'' +
                ", weight=" + weight +
                ", flow='" + flow + '\'' +
                ", flowDirection='" + flowDirection + '\'' +
                ", dispatchCount=" + dispatchCount +
                ", exception=" + exception +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", savePoints=" + savePoints +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Long estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Long getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(Long latestDate) {
        this.latestDate = latestDate;
    }

    public String getLatestInfo() {
        return latestInfo;
    }

    public void setLatestInfo(String latestInfo) {
        this.latestInfo = latestInfo;
    }

    public String getLatestPlace() {
        return latestPlace;
    }

    public void setLatestPlace(String latestPlace) {
        this.latestPlace = latestPlace;
    }

    public String getLatestStatus() {
        return latestStatus;
    }

    public void setLatestStatus(String latestStatus) {
        this.latestStatus = latestStatus;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeTelNo() {
        return consigneeTelNo;
    }

    public void setConsigneeTelNo(String consigneeTelNo) {
        this.consigneeTelNo = consigneeTelNo;
    }

    public String getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy;
    }

    public String getHeadCompany() {
        return headCompany;
    }

    public void setHeadCompany(String headCompany) {
        this.headCompany = headCompany;
    }

    public String getTailCompany() {
        return tailCompany;
    }

    public void setTailCompany(String tailCompany) {
        this.tailCompany = tailCompany;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getFlowDirection() {
        return flowDirection;
    }

    public void setFlowDirection(String flowDirection) {
        this.flowDirection = flowDirection;
    }

    public Integer getDispatchCount() {
        return dispatchCount;
    }

    public void setDispatchCount(Integer dispatchCount) {
        this.dispatchCount = dispatchCount;
    }

    public boolean getException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public List<SavePoint> getSavePoints() {
        return savePoints;
    }

    public void setSavePoints(List<SavePoint> savePoints) {
        this.savePoints = savePoints;
    }

    public static class SavePoint implements Serializable {

        private String place;
        private Long date;
        private String status;
        private String info;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public Long getDate() {
            return date;
        }

        public void setDate(Long date) {
            this.date = date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "SavePoint{" +
                    "place='" + place + '\'' +
                    ", date=" + date +
                    ", status='" + status + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}
