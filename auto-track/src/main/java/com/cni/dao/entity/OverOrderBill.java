package com.cni.dao.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//TODO 改继承
@Document
public class OverOrderBill implements Serializable {


    private ObjectId _id;
    //归档时间
    private Date archiveTime;
    //以下都是TrackOrderDocument的属性
    @Id
    private String number;
    private String origin;
    private String destination;
    private Long estimatedDate;
    private String customer;
    private String consignee;
    private String consigneeTelNo;
    private String receiveBy;
    private String headCompany;
    private String tailCompany;
    private String referenceNo;
    private String packageType;
    private Double weight;
    private String flow;
    private String flowDirection;
    private Integer dispatchCount;
    private boolean exception;
    private String exceptionDetail;
    private List<InfoNode> infoNodes = new ArrayList<>();

    public OverOrderBill() {}

    public OverOrderBill(OrderBill orderBill) {
        //保护性拷贝
        OrderBill copy;
        try {
            copy=(OrderBill) orderBill.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.err.println("创建对象失败，克隆失败");
            return;
        }
        archiveTime= new Date();
        number=copy.getNumber();
        origin=copy.getOrigin();
        destination=copy.getDestination();
        estimatedDate=copy.getEstimatedDate();
        customer=copy.getCustomer();
        consignee=copy.getConsignee();
        consigneeTelNo=copy.getConsigneeTelNo();
        receiveBy=copy.getReceiveBy();
        headCompany=copy.getHeadCompany();
        tailCompany=copy.getTailCompany();
        referenceNo=copy.getReferenceNo();
        packageType=copy.getPackageType();
        weight=copy.getWeight();
        flow=copy.getFlow();
        flowDirection=copy.getFlowDirection();
        dispatchCount=copy.getDispatchCount();
        exception=copy.getException();
        exceptionDetail=copy.getExceptionDetail();
        infoNodes = copy.getScans().stream()
                .map(infoNode -> {
                    InfoNode infoNode1 = new InfoNode();
                    infoNode1.setDate(infoNode.getDate());
                    infoNode1.setInfo(infoNode.getInfo());
                    infoNode1.setPlace(infoNode.getPlace());
                    infoNode1.setStatus(infoNode.getStatus());
                    return infoNode1;
                }).sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).collect(Collectors.toList());
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public boolean isException() {
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

    public List<InfoNode> getInfoNodes() {
        return infoNodes;
    }

    public void setScans(List<InfoNode> infoNodes) {
        this.infoNodes = infoNodes;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Date getCompletedTime() {
        return archiveTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.archiveTime = archiveTime;
    }

    @Override
    public String toString() {
        return "OverOrderBill{" +
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
                ", infoNodes=" + infoNodes +
                ", archiveTime=" + archiveTime +
                '}';
    }

    public static class InfoNode implements Serializable {
        /**
         * place :
         * date : 1512030005816
         * status : Shipment Created
         * info : Consignment Manifested
         */



        private String place;
        private Long date;
        private String status;
        private String info;

        public InfoNode(){}

        public InfoNode(String place, Long date, String status, String info) {
            this.place = place;
            this.date = date;
            this.status = status;
            this.info = info;
        }

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
            return "InfoNode{" +
                    "place='" + place + '\'' +
                    ", date=" + date +
                    ", status='" + status + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

}
