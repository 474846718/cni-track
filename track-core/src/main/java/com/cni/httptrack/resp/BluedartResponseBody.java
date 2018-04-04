package com.cni.httptrack.resp;

import java.util.List;

/**
 * Bluedart公司原生API
 * <p>
 * Created by CNI on 2018/1/23.
 */
public class BluedartResponseBody {

    /**
     * code : 200
     * message : 原生API调用成功
     * info : {"shipment":[{"prodcode":"A","service":"Dart Apex - Door To Door","pickUpDate":"06 December 2017","pickUpTime":1532,"origin":"MUMBAI","originAreaCode":"BOM","destination":"JALANDHAR","destinationAreaCode":"JRD","productType":"Non Documents","customerCode":383530,"customerName":"SINO INDIA ETAIL PVT LTD","senderName":"SIE","toAttention":"Ms. Anisha sharma","consignee":"Ms. Anisha sharma","consigneeAddress1":"Jalandhar,74 sahibzada ajit si","consigneeAddress2":"ngh nagar More departmental st","consigneeAddress3":"ore","consigneePincode":144001,"consigneeTelNo":8284966579,"weight":1.5,"status":"SHIPMENT DELIVERED","statusType":"DL","expectedDeliveryDate":"08 December 2017","statusDate":"08 December 2017","statusTime":"18:27","newWaybillNo":null,"receivedBy":"SELF","instructions":null,"scans":{"scanDetail":[{"scan":"SHIPMENT DELIVERED","scanCode":0,"scanType":"DL","scanGroupType":"T","scanDate":"08-Dec-2017","scanTime":"18:27","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"NECESSARY CHARGES PENDING FROM CONSIGNEE","scanCode":62,"scanType":"UD","scanGroupType":"T","scanDate":"08-Dec-2017","scanTime":"18:26","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"12:47","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"10:53","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"22:01","scannedLocation":"RAMA ROAD HUB","scannedLocationCode":"RRH"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"11:32","scannedLocation":"RAMA ROAD HUB","scannedLocationCode":"RRH"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"07:40","scannedLocation":"DELHI HUB","scannedLocationCode":"DUB"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"07:07","scannedLocation":"DELHI HUB","scannedLocationCode":"DUB"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"04:22","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"20:16","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"18:36","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"15:32","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"}]},"waybillNo":59638517234,"refNo":"200743381E12022105"},{"prodcode":"A","service":"Dart Apex - Door To Door","pickUpDate":"07 December 2017","pickUpTime":1258,"origin":"MUMBAI","originAreaCode":"BOM","destination":"BANGALORE","destinationAreaCode":"BLR","productType":"Non Documents","customerCode":383530,"customerName":"SINO INDIA ETAIL PVT LTD","senderName":"SIE","toAttention":"Ms. Priyanka shil","consignee":"Ms. Priyanka shil","consigneeAddress1":"Near manipal hospital,Crescent","consigneeAddress2":"3, prestige shantiniketan, Wh","consigneeAddress3":"itefield,hoodi Crescent 3","consigneePincode":560048,"consigneeTelNo":7349083714,"weight":0.5,"status":"RETURNED TO ORIGIN AT SHIPPER'S REQUEST","statusType":"RT","expectedDeliveryDate":"08 December 2017","statusDate":"14 December 2017","statusTime":"17:45","newWaybillNo":42057026524,"receivedBy":null,"instructions":null,"scans":{"scanDetail":[{"scan":"RETURNED TO ORIGIN AT SHIPPER'S REQUEST","scanCode":74,"scanType":"RT","scanGroupType":"T","scanDate":"14-Dec-2017","scanTime":"17:45","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"UNDELIVERED SHIPMENT HELD AT LOCATION","scanCode":24,"scanType":"UD","scanGroupType":"S","scanDate":"14-Dec-2017","scanTime":"10:44","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"NEED DEPARTMENT NAME/EXTENTION NUMBER","scanCode":139,"scanType":"UD","scanGroupType":"T","scanDate":"13-Dec-2017","scanTime":"17:03","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"13-Dec-2017","scanTime":"09:31","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"NEED DEPARTMENT NAME/EXTENTION NUMBER","scanCode":139,"scanType":"UD","scanGroupType":"T","scanDate":"12-Dec-2017","scanTime":"12:36","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"12-Dec-2017","scanTime":"09:37","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"NEED DEPARTMENT NAME/EXTENTION NUMBER","scanCode":139,"scanType":"UD","scanGroupType":"T","scanDate":"11-Dec-2017","scanTime":"15:10","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"11-Dec-2017","scanTime":"09:40","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"HOLIDAY, DELIVERY ON NEXT BUSINESS DAY","scanCode":5,"scanType":"UD","scanGroupType":"T","scanDate":"09-Dec-2017","scanTime":"10:42","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"NEED DEPARTMENT NAME/EXTENTION NUMBER","scanCode":139,"scanType":"UD","scanGroupType":"T","scanDate":"08-Dec-2017","scanTime":"15:41","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"13:00","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"11:54","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"09:30","scannedLocation":"BIAL HUB","scannedLocationCode":"BIA"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"08:06","scannedLocation":"BIAL HUB","scannedLocationCode":"BIA"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"05:08","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"18:51","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"17:53","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"12:58","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"}]},"waybillNo":59638108526,"refNo":"199644521E12010250"},{"prodcode":"D","service":"Domestic Priority","pickUpDate":"14 December 2017","pickUpTime":1745,"origin":"BANGALORE","originAreaCode":"BLR","destination":"MUMBAI","destinationAreaCode":"BOM","productType":"Non Documents","customerCode":99960,"customerName":"BLUE DART EXPRESS LTD","senderName":"BLUE DART EXPRESS LT","toAttention":"SINO INDIA ETAIL PVT","consignee":"SINO INDIA ETAIL PVT LTD","consigneeAddress1":"SINO INDIA ETAIL PVT LTD","consigneeAddress2":"802, Sumer Plaza","consigneeAddress3":"Marol Maroshi Rd, Andheri","consigneePincode":400059,"consigneeTelNo":null,"weight":0.5,"status":"SHIPMENT DELIVERED","statusType":"DL","expectedDeliveryDate":"16 December 2017","statusDate":"15 December 2017","statusTime":"11:39","newWaybillNo":null,"receivedBy":"CO STAMP","instructions":"Cut Out Skinny Leggi","scans":{"scanDetail":[{"scan":"SHIPMENT DELIVERED","scanCode":0,"scanType":"DL","scanGroupType":"T","scanDate":"15-Dec-2017","scanTime":"11:39","scannedLocation":"SEEPZ","scannedLocationCode":"SPZ"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"10:02","scannedLocation":"SEEPZ","scannedLocationCode":"SPZ"},{"scan":"SHIPMENT ARRIVED","scanCode":9,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"07:45","scannedLocation":"SEEPZ","scannedLocationCode":"SPZ"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"06:52","scannedLocation":"MUMBAI HUB","scannedLocationCode":"BOB"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"04:18","scannedLocation":"MUMBAI HUB","scannedLocationCode":"BOB"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"02:47","scannedLocation":"BIAL HUB","scannedLocationCode":"BIA"},{"scan":"SHIPMENT ARRIVED","scanCode":7,"scanType":"UD","scanGroupType":"S","scanDate":"15-Dec-2017","scanTime":"01:41","scannedLocation":"BIAL HUB","scannedLocationCode":"BIA"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"14-Dec-2017","scanTime":"22:03","scannedLocation":"INTERNATIONAL TECH PARK","scannedLocationCode":"ITP"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"14-Dec-2017","scanTime":"18:26","scannedLocation":"INTERNATIONAL TECH PARK","scannedLocationCode":"ITP"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"14-Dec-2017","scanTime":"17:45","scannedLocation":"MAHADEVAPURA INDUS. AREA","scannedLocationCode":"MIA"}]},"waybillNo":42057026524,"refNo":"074 - 59638108526"}]}
     * success : true
     */

    private String code;
    private String message;
    private InfoBean info;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BluedartResponseBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", info=" + info +
                ", success=" + success +
                '}';
    }

    public static class InfoBean {
        private List<ShipmentBean> shipment;

        public List<ShipmentBean> getShipment() {
            return shipment;
        }

        public void setShipment(List<ShipmentBean> shipment) {
            this.shipment = shipment;
        }

        @Override
        public String toString() {
            return "InfoBean{" +
                    "shipment=" + shipment +
                    '}';
        }

        public static class ShipmentBean {
            /**
             * prodcode : A
             * service : Dart Apex - Door To Door
             * pickUpDate : 06 December 2017
             * pickUpTime : 1532
             * origin : MUMBAI
             * originAreaCode : BOM
             * destination : JALANDHAR
             * destinationAreaCode : JRD
             * productType : Non Documents
             * customerCode : 383530
             * customerName : SINO INDIA ETAIL PVT LTD
             * senderName : SIE
             * toAttention : Ms. Anisha sharma
             * consignee : Ms. Anisha sharma
             * consigneeAddress1 : Jalandhar,74 sahibzada ajit si
             * consigneeAddress2 : ngh nagar More departmental st
             * consigneeAddress3 : ore
             * consigneePincode : 144001
             * consigneeTelNo : 8284966579
             * weight : 1.5
             * status : SHIPMENT DELIVERED
             * statusType : DL
             * expectedDeliveryDate : 08 December 2017
             * statusDate : 08 December 2017
             * statusTime : 18:27
             * newWaybillNo : null
             * receivedBy : SELF
             * instructions : null
             * scans : {"scanDetail":[{"scan":"SHIPMENT DELIVERED","scanCode":0,"scanType":"DL","scanGroupType":"T","scanDate":"08-Dec-2017","scanTime":"18:27","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"NECESSARY CHARGES PENDING FROM CONSIGNEE","scanCode":62,"scanType":"UD","scanGroupType":"T","scanDate":"08-Dec-2017","scanTime":"18:26","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT OUT FOR DELIVERY","scanCode":2,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"12:47","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"08-Dec-2017","scanTime":"10:53","scannedLocation":"ITO SERVICE CENTRE","scannedLocationCode":"JRC"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"22:01","scannedLocation":"RAMA ROAD HUB","scannedLocationCode":"RRH"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"11:32","scannedLocation":"RAMA ROAD HUB","scannedLocationCode":"RRH"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"07:40","scannedLocation":"DELHI HUB","scannedLocationCode":"DUB"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"07:07","scannedLocation":"DELHI HUB","scannedLocationCode":"DUB"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"07-Dec-2017","scanTime":"04:22","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT ARRIVED AT HUB","scanCode":20,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"20:16","scannedLocation":"MUMBAI ETAIL WAREHOU","scannedLocationCode":"BEW"},{"scan":"SHIPMENT FURTHER CONNECTED","scanCode":3,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"18:36","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"},{"scan":"SHIPMENT ARRIVED","scanCode":1,"scanType":"UD","scanGroupType":"S","scanDate":"06-Dec-2017","scanTime":"15:32","scannedLocation":"MUMBAI ETAIL CENTRE","scannedLocationCode":"MEC"}]}
             * waybillNo : 59638517234
             * refNo : 200743381E12022105
             */

            private String prodcode;
            private String service;
            private String pickUpDate;
            private int pickUpTime;
            private String origin;
            private String originAreaCode;
            private String destination;
            private String destinationAreaCode;
            private String productType;
            private int customerCode;
            private String customerName;
            private String senderName;
            private String toAttention;
            private String consignee;
            private String consigneeAddress1;
            private String consigneeAddress2;
            private String consigneeAddress3;
            private int consigneePincode;
            private String consigneeTelNo;
            private double weight;
            private String status;
            private String statusType;
            private String expectedDeliveryDate;
            private String statusDate;
            private String statusTime;
            private Object newWaybillNo;
            private String receivedBy;
            private String relation;
            private String idType;
            private String idNo;
            private Object instructions;
            private ScansBean scans;
            private long waybillNo;
            private String refNo;

            public String getConsigneeTelNo() {
                return consigneeTelNo;
            }

            @Override
            public String toString() {
                return "ShipmentBean{" +
                        "prodcode='" + prodcode + '\'' +
                        ", service='" + service + '\'' +
                        ", pickUpDate='" + pickUpDate + '\'' +
                        ", pickUpTime=" + pickUpTime +
                        ", origin='" + origin + '\'' +
                        ", originAreaCode='" + originAreaCode + '\'' +
                        ", destination='" + destination + '\'' +
                        ", destinationAreaCode='" + destinationAreaCode + '\'' +
                        ", productType='" + productType + '\'' +
                        ", customerCode=" + customerCode +
                        ", customerName='" + customerName + '\'' +
                        ", senderName='" + senderName + '\'' +
                        ", toAttention='" + toAttention + '\'' +
                        ", consignee='" + consignee + '\'' +
                        ", consigneeAddress1='" + consigneeAddress1 + '\'' +
                        ", consigneeAddress2='" + consigneeAddress2 + '\'' +
                        ", consigneeAddress3='" + consigneeAddress3 + '\'' +
                        ", consigneePincode=" + consigneePincode +
                        ", consigneeTelNo=" + consigneeTelNo +
                        ", weight=" + weight +
                        ", status='" + status + '\'' +
                        ", statusType='" + statusType + '\'' +
                        ", expectedDeliveryDate='" + expectedDeliveryDate + '\'' +
                        ", statusDate='" + statusDate + '\'' +
                        ", statusTime='" + statusTime + '\'' +
                        ", newWaybillNo=" + newWaybillNo +
                        ", receivedBy='" + receivedBy + '\'' +
                        ", instructions=" + instructions +
                        ", scans=" + scans +
                        ", waybillNo=" + waybillNo +
                        ", refNo='" + refNo + '\'' +
                        '}';
            }

            public void setConsigneeTelNo(String consigneeTelNo) {
                this.consigneeTelNo = consigneeTelNo;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getIdType() {
                return idType;
            }

            public void setIdType(String idType) {
                this.idType = idType;
            }

            public String getIdNo() {
                return idNo;
            }

            public void setIdNo(String idNo) {
                this.idNo = idNo;
            }

            public String getProdcode() {
                return prodcode;
            }

            public void setProdcode(String prodcode) {
                this.prodcode = prodcode;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public String getPickUpDate() {
                return pickUpDate;
            }

            public void setPickUpDate(String pickUpDate) {
                this.pickUpDate = pickUpDate;
            }

            public int getPickUpTime() {
                return pickUpTime;
            }

            public void setPickUpTime(int pickUpTime) {
                this.pickUpTime = pickUpTime;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getOriginAreaCode() {
                return originAreaCode;
            }

            public void setOriginAreaCode(String originAreaCode) {
                this.originAreaCode = originAreaCode;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public String getDestinationAreaCode() {
                return destinationAreaCode;
            }

            public void setDestinationAreaCode(String destinationAreaCode) {
                this.destinationAreaCode = destinationAreaCode;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public int getCustomerCode() {
                return customerCode;
            }

            public void setCustomerCode(int customerCode) {
                this.customerCode = customerCode;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getSenderName() {
                return senderName;
            }

            public void setSenderName(String senderName) {
                this.senderName = senderName;
            }

            public String getToAttention() {
                return toAttention;
            }

            public void setToAttention(String toAttention) {
                this.toAttention = toAttention;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getConsigneeAddress1() {
                return consigneeAddress1;
            }

            public void setConsigneeAddress1(String consigneeAddress1) {
                this.consigneeAddress1 = consigneeAddress1;
            }

            public String getConsigneeAddress2() {
                return consigneeAddress2;
            }

            public void setConsigneeAddress2(String consigneeAddress2) {
                this.consigneeAddress2 = consigneeAddress2;
            }

            public String getConsigneeAddress3() {
                return consigneeAddress3;
            }

            public void setConsigneeAddress3(String consigneeAddress3) {
                this.consigneeAddress3 = consigneeAddress3;
            }

            public int getConsigneePincode() {
                return consigneePincode;
            }

            public void setConsigneePincode(int consigneePincode) {
                this.consigneePincode = consigneePincode;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatusType() {
                return statusType;
            }

            public void setStatusType(String statusType) {
                this.statusType = statusType;
            }

            public String getExpectedDeliveryDate() {
                return expectedDeliveryDate;
            }

            public void setExpectedDeliveryDate(String expectedDeliveryDate) {
                this.expectedDeliveryDate = expectedDeliveryDate;
            }

            public String getStatusDate() {
                return statusDate;
            }

            public void setStatusDate(String statusDate) {
                this.statusDate = statusDate;
            }

            public String getStatusTime() {
                return statusTime;
            }

            public void setStatusTime(String statusTime) {
                this.statusTime = statusTime;
            }

            public Object getNewWaybillNo() {
                return newWaybillNo;
            }

            public void setNewWaybillNo(Object newWaybillNo) {
                this.newWaybillNo = newWaybillNo;
            }

            public String getReceivedBy() {
                return receivedBy;
            }

            public void setReceivedBy(String receivedBy) {
                this.receivedBy = receivedBy;
            }

            public Object getInstructions() {
                return instructions;
            }

            public void setInstructions(Object instructions) {
                this.instructions = instructions;
            }

            public ScansBean getScans() {
                return scans;
            }

            public void setScans(ScansBean scans) {
                this.scans = scans;
            }

            public long getWaybillNo() {
                return waybillNo;
            }

            public void setWaybillNo(long waybillNo) {
                this.waybillNo = waybillNo;
            }

            public String getRefNo() {
                return refNo;
            }

            public void setRefNo(String refNo) {
                this.refNo = refNo;
            }

            public static class ScansBean {
                private List<ScanDetailBean> scanDetail;

                public List<ScanDetailBean> getScanDetail() {
                    return scanDetail;
                }

                public void setScanDetail(List<ScanDetailBean> scanDetail) {
                    this.scanDetail = scanDetail;
                }

                public static class ScanDetailBean {
                    /**
                     * scan : SHIPMENT DELIVERED
                     * scanCode : 0
                     * scanType : DL
                     * scanGroupType : T
                     * scanDate : 08-Dec-2017
                     * scanTime : 18:27
                     * scannedLocation : ITO SERVICE CENTRE
                     * scannedLocationCode : JRC
                     */

                    private String scan;
                    private int scanCode;
                    private String scanType;
                    private String scanGroupType;
                    private String scanDate;
                    private String scanTime;
                    private String scannedLocation;
                    private String scannedLocationCode;

                    public String getScan() {
                        return scan;
                    }

                    public void setScan(String scan) {
                        this.scan = scan;
                    }

                    public int getScanCode() {
                        return scanCode;
                    }

                    public void setScanCode(int scanCode) {
                        this.scanCode = scanCode;
                    }

                    public String getScanType() {
                        return scanType;
                    }

                    public void setScanType(String scanType) {
                        this.scanType = scanType;
                    }

                    public String getScanGroupType() {
                        return scanGroupType;
                    }

                    public void setScanGroupType(String scanGroupType) {
                        this.scanGroupType = scanGroupType;
                    }

                    public String getScanDate() {
                        return scanDate;
                    }

                    public void setScanDate(String scanDate) {
                        this.scanDate = scanDate;
                    }

                    public String getScanTime() {
                        return scanTime;
                    }

                    public void setScanTime(String scanTime) {
                        this.scanTime = scanTime;
                    }

                    public String getScannedLocation() {
                        return scannedLocation;
                    }

                    public void setScannedLocation(String scannedLocation) {
                        this.scannedLocation = scannedLocation;
                    }

                    public String getScannedLocationCode() {
                        return scannedLocationCode;
                    }

                    public void setScannedLocationCode(String scannedLocationCode) {
                        this.scannedLocationCode = scannedLocationCode;
                    }

                    @Override
                    public String toString() {
                        return "ScanDetailBean{" +
                                "scan='" + scan + '\'' +
                                ", scanCode=" + scanCode +
                                ", scanType='" + scanType + '\'' +
                                ", scanGroupType='" + scanGroupType + '\'' +
                                ", scanDate='" + scanDate + '\'' +
                                ", scanTime='" + scanTime + '\'' +
                                ", scannedLocation='" + scannedLocation + '\'' +
                                ", scannedLocationCode='" + scannedLocationCode + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
