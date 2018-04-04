package com.cni.httptrack.resp;

import java.util.List;

/**
 * Delhivery公司原生API
 * <p>
 * Created by CNI on 2018/1/23.
 */
public class DelhiveryResponseBody {

    /**
     * code : 200
     * message : 原生API调用成功
     * info : {"meta":{"requestId":"31a427f9-ffd8-11e7-ac3c-0f9b2ef3a86e"},"data":[{"status":{"status":"DELIVERED_SELLER","statusDateTime":"2017-12-19T20:28:08.197","statusType":"DL","instructions":"RETURN Accepted"},"estimatedDate":"2017-12-14T23:59:59","flow":"FORWARD","flowDirection":"RETURN","dispatchCount":3,"referenceNo":"201853121E12070935","packageType":"PREPAID","awb":"1521511465785","destination":"Patiala","scans":[{"scanDateTime":"2017-12-07T07:05:11.157000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Consignment Manifested","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-08T03:40:55","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-09T03:04:43","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T00:57:57","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T08:40:45","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-11T04:52:55.430000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Package not picked/recieved from client","status":"NOT_PICKED"},{"scanDateTime":"2017-12-11T14:31:59","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Picked Up from Client Location","status":"PICKUP"},{"scanDateTime":"2017-12-11T15:26:39.480000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Recieved at Origin Center","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-11T18:03:17.287000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T03:23:46.847000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment dispatched from origin city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T13:46:51.553000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T05:29:10.610000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received at destination city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T08:46:42.125000","scannedLocation":"Patiala (Punjab)","instructions":"Received at destination city","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-13T11:00:39.339000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-13T15:08:52.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-14T11:01:00.940000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-14T15:56:24.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T11:57:54.195000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-15T17:27:06.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T20:00:19.667000","scannedLocation":"Patiala (Punjab)","instructions":"Reached maximum attempt count","status":"RETURNED"},{"scanDateTime":"2017-12-15T22:25:50.428000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-16T00:14:57.369000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment dispatched from destination city","status":"RETURNED"},{"scanDateTime":"2017-12-16T08:24:43.557000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-17T09:49:51.761000","scannedLocation":"Gurgaon_Bilaspur_HB (Haryana)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T19:32:46.297000","scannedLocation":"Bhiwandi_Mankoli_HB (Maharashtra)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T22:20:13.377000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T08:00:37.642000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T11:46:38.493000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Dispatched for RTO","status":"OUT_DELIVERY_SELLER"},{"scanDateTime":"2017-12-19T20:28:08.197000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"RETURN Accepted","status":"DELIVERED_SELLER"}]},{"status":{"status":"DELIVERED_SELLER","statusDateTime":"2017-12-26T19:38:22.994","statusType":"DL","instructions":"RETURN Accepted"},"estimatedDate":"2017-12-25T23:59:59","flow":"REVERSE","flowDirection":"ONWARD","dispatchCount":2,"referenceNo":"764389E12152029","packageType":"PICKUP","awb":"1521511829914","destination":"Pune","scans":[{"scanDateTime":"2017-12-15T17:59:10.454000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Received soft data for consignment.","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-23T09:54:03.903000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Out for pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-23T17:53:00.171000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Request for delayed pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-24T12:05:10.688000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Out for pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-24T12:05:31.002000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"DTO/REPL: Pick Up Completed","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-25T16:55:22.677000","scannedLocation":"Pune_Tathawde_H (Maharashtra)","instructions":"Consignment received","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T02:39:42.159000","scannedLocation":"Pune_Tathawde_H (Maharashtra)","instructions":"Consignment dispatched from destination city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T06:01:05.258000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received at return city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T07:37:08.005000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Consignment received at return city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T09:42:06.459000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Dispatched for RTO","status":"OUT_DELIVERY_SELLER"},{"scanDateTime":"2017-12-26T19:38:22.994000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"RETURN Accepted","status":"DELIVERED_SELLER"}]}]}
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
        return "DelhiveryResponseBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", info=" + info +
                ", success=" + success +
                '}';
    }

    public static class InfoBean {
        /**
         * meta : {"requestId":"31a427f9-ffd8-11e7-ac3c-0f9b2ef3a86e"}
         * data : [{"status":{"status":"DELIVERED_SELLER","statusDateTime":"2017-12-19T20:28:08.197","statusType":"DL","instructions":"RETURN Accepted"},"estimatedDate":"2017-12-14T23:59:59","flow":"FORWARD","flowDirection":"RETURN","dispatchCount":3,"referenceNo":"201853121E12070935","packageType":"PREPAID","awb":"1521511465785","destination":"Patiala","scans":[{"scanDateTime":"2017-12-07T07:05:11.157000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Consignment Manifested","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-08T03:40:55","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-09T03:04:43","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T00:57:57","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T08:40:45","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-11T04:52:55.430000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Package not picked/recieved from client","status":"NOT_PICKED"},{"scanDateTime":"2017-12-11T14:31:59","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Picked Up from Client Location","status":"PICKUP"},{"scanDateTime":"2017-12-11T15:26:39.480000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Recieved at Origin Center","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-11T18:03:17.287000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T03:23:46.847000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment dispatched from origin city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T13:46:51.553000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T05:29:10.610000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received at destination city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T08:46:42.125000","scannedLocation":"Patiala (Punjab)","instructions":"Received at destination city","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-13T11:00:39.339000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-13T15:08:52.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-14T11:01:00.940000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-14T15:56:24.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T11:57:54.195000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-15T17:27:06.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T20:00:19.667000","scannedLocation":"Patiala (Punjab)","instructions":"Reached maximum attempt count","status":"RETURNED"},{"scanDateTime":"2017-12-15T22:25:50.428000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-16T00:14:57.369000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment dispatched from destination city","status":"RETURNED"},{"scanDateTime":"2017-12-16T08:24:43.557000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-17T09:49:51.761000","scannedLocation":"Gurgaon_Bilaspur_HB (Haryana)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T19:32:46.297000","scannedLocation":"Bhiwandi_Mankoli_HB (Maharashtra)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T22:20:13.377000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T08:00:37.642000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T11:46:38.493000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Dispatched for RTO","status":"OUT_DELIVERY_SELLER"},{"scanDateTime":"2017-12-19T20:28:08.197000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"RETURN Accepted","status":"DELIVERED_SELLER"}]},{"status":{"status":"DELIVERED_SELLER","statusDateTime":"2017-12-26T19:38:22.994","statusType":"DL","instructions":"RETURN Accepted"},"estimatedDate":"2017-12-25T23:59:59","flow":"REVERSE","flowDirection":"ONWARD","dispatchCount":2,"referenceNo":"764389E12152029","packageType":"PICKUP","awb":"1521511829914","destination":"Pune","scans":[{"scanDateTime":"2017-12-15T17:59:10.454000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Received soft data for consignment.","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-23T09:54:03.903000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Out for pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-23T17:53:00.171000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Request for delayed pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-24T12:05:10.688000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"Out for pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-24T12:05:31.002000","scannedLocation":"Pune_Pashan_DC (Maharashtra)","instructions":"DTO/REPL: Pick Up Completed","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-25T16:55:22.677000","scannedLocation":"Pune_Tathawde_H (Maharashtra)","instructions":"Consignment received","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T02:39:42.159000","scannedLocation":"Pune_Tathawde_H (Maharashtra)","instructions":"Consignment dispatched from destination city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T06:01:05.258000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received at return city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T07:37:08.005000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Consignment received at return city","status":"PROD_REPLACED"},{"scanDateTime":"2017-12-26T09:42:06.459000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Dispatched for RTO","status":"OUT_DELIVERY_SELLER"},{"scanDateTime":"2017-12-26T19:38:22.994000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"RETURN Accepted","status":"DELIVERED_SELLER"}]}]
         */

        private MetaBean meta;
        private List<DataBean> data;

        public MetaBean getMeta() {
            return meta;
        }

        public void setMeta(MetaBean meta) {
            this.meta = meta;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "InfoBean{" +
                    "meta=" + meta +
                    ", data=" + data +
                    '}';
        }

        public static class MetaBean {
            /**
             * requestId : 31a427f9-ffd8-11e7-ac3c-0f9b2ef3a86e
             */

            private String requestId;

            public String getRequestId() {
                return requestId;
            }

            public void setRequestId(String requestId) {
                this.requestId = requestId;
            }

            @Override
            public String toString() {
                return "MetaBean{" +
                        "requestId='" + requestId + '\'' +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * status : {"status":"DELIVERED_SELLER","statusDateTime":"2017-12-19T20:28:08.197","statusType":"DL","instructions":"RETURN Accepted"}
             * estimatedDate : 2017-12-14T23:59:59
             * flow : FORWARD
             * flowDirection : RETURN
             * dispatchCount : 3
             * referenceNo : 201853121E12070935
             * packageType : PREPAID
             * awb : 1521511465785
             * destination : Patiala
             * scans : [{"scanDateTime":"2017-12-07T07:05:11.157000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Consignment Manifested","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-08T03:40:55","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-09T03:04:43","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T00:57:57","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-10T08:40:45","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Out for Pickup","status":"WAITING_PICKUP"},{"scanDateTime":"2017-12-11T04:52:55.430000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Package not picked/recieved from client","status":"NOT_PICKED"},{"scanDateTime":"2017-12-11T14:31:59","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Picked Up from Client Location","status":"PICKUP"},{"scanDateTime":"2017-12-11T15:26:39.480000","scannedLocation":"Mumbai_Chndivli_PC (Maharashtra)","instructions":"Shipment Recieved at Origin Center","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-11T18:03:17.287000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T03:23:46.847000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment dispatched from origin city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-12T13:46:51.553000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T05:29:10.610000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received at destination city","status":"IN_TRANSIT"},{"scanDateTime":"2017-12-13T08:46:42.125000","scannedLocation":"Patiala (Punjab)","instructions":"Received at destination city","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-13T11:00:39.339000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-13T15:08:52.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-14T11:01:00.940000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-14T15:56:24.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T11:57:54.195000","scannedLocation":"Patiala (Punjab)","instructions":"Out for delivery","status":"OUT_DELIVERY"},{"scanDateTime":"2017-12-15T17:27:06.002000","scannedLocation":"Patiala (Punjab)","instructions":"Entry restricted area","status":"REACHED_DEST_CITY"},{"scanDateTime":"2017-12-15T20:00:19.667000","scannedLocation":"Patiala (Punjab)","instructions":"Reached maximum attempt count","status":"RETURNED"},{"scanDateTime":"2017-12-15T22:25:50.428000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-16T00:14:57.369000","scannedLocation":"Patiala_DPC (Punjab)","instructions":"Consignment dispatched from destination city","status":"RETURNED"},{"scanDateTime":"2017-12-16T08:24:43.557000","scannedLocation":"Chandigarh_Hub (Chandigarh)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-17T09:49:51.761000","scannedLocation":"Gurgaon_Bilaspur_HB (Haryana)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T19:32:46.297000","scannedLocation":"Bhiwandi_Mankoli_HB (Maharashtra)","instructions":"Consignment received","status":"RETURNED"},{"scanDateTime":"2017-12-18T22:20:13.377000","scannedLocation":"Mumbai Hub (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T08:00:37.642000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Consignment received at return city","status":"RETURNED"},{"scanDateTime":"2017-12-19T11:46:38.493000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"Dispatched for RTO","status":"OUT_DELIVERY_SELLER"},{"scanDateTime":"2017-12-19T20:28:08.197000","scannedLocation":"BOM_Sakinaka_RP (Maharashtra)","instructions":"RETURN Accepted","status":"DELIVERED_SELLER"}]
             */

            private StatusBean status;
            private String estimatedDate;
            private String flow;
            private String flowDirection;
            private int dispatchCount;
            private String referenceNo;
            private String packageType;
            private String awb;
            private String destination;
            private List<ScansBean> scans;

            public StatusBean getStatus() {
                return status;
            }

            public void setStatus(StatusBean status) {
                this.status = status;
            }

            public String getEstimatedDate() {
                return estimatedDate;
            }

            public void setEstimatedDate(String estimatedDate) {
                this.estimatedDate = estimatedDate;
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

            public int getDispatchCount() {
                return dispatchCount;
            }

            public void setDispatchCount(int dispatchCount) {
                this.dispatchCount = dispatchCount;
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

            public String getAwb() {
                return awb;
            }

            public void setAwb(String awb) {
                this.awb = awb;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public List<ScansBean> getScans() {
                return scans;
            }

            public void setScans(List<ScansBean> scans) {
                this.scans = scans;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "status=" + status +
                        ", estimatedDate='" + estimatedDate + '\'' +
                        ", flow='" + flow + '\'' +
                        ", flowDirection='" + flowDirection + '\'' +
                        ", dispatchCount=" + dispatchCount +
                        ", referenceNo='" + referenceNo + '\'' +
                        ", packageType='" + packageType + '\'' +
                        ", awb='" + awb + '\'' +
                        ", destination='" + destination + '\'' +
                        ", scans=" + scans +
                        '}';
            }

            public static class StatusBean {
                /**
                 * status : DELIVERED_SELLER
                 * statusDateTime : 2017-12-19T20:28:08.197
                 * statusType : DL
                 * instructions : RETURN Accepted
                 */

                private String status;
                private String statusDateTime;
                private String statusType;
                private String instructions;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getStatusDateTime() {
                    return statusDateTime;
                }

                public void setStatusDateTime(String statusDateTime) {
                    this.statusDateTime = statusDateTime;
                }

                public String getStatusType() {
                    return statusType;
                }

                public void setStatusType(String statusType) {
                    this.statusType = statusType;
                }

                public String getInstructions() {
                    return instructions;
                }

                public void setInstructions(String instructions) {
                    this.instructions = instructions;
                }

                @Override
                public String toString() {
                    return "StatusBean{" +
                            "status='" + status + '\'' +
                            ", statusDateTime='" + statusDateTime + '\'' +
                            ", statusType='" + statusType + '\'' +
                            ", instructions='" + instructions + '\'' +
                            '}';
                }
            }

            public static class ScansBean {
                /**
                 * scanDateTime : 2017-12-07T07:05:11.157000
                 * scannedLocation : Mumbai_Chndivli_PC (Maharashtra)
                 * instructions : Consignment Manifested
                 * status : WAITING_PICKUP
                 */

                private String scanDateTime;
                private String scannedLocation;
                private String instructions;
                private String status;

                public String getScanDateTime() {
                    return scanDateTime;
                }

                public void setScanDateTime(String scanDateTime) {
                    this.scanDateTime = scanDateTime;
                }

                public String getScannedLocation() {
                    return scannedLocation;
                }

                public void setScannedLocation(String scannedLocation) {
                    this.scannedLocation = scannedLocation;
                }

                public String getInstructions() {
                    return instructions;
                }

                public void setInstructions(String instructions) {
                    this.instructions = instructions;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                @Override
                public String toString() {
                    return "ScansBean{" +
                            "scanDateTime='" + scanDateTime + '\'' +
                            ", scannedLocation='" + scannedLocation + '\'' +
                            ", instructions='" + instructions + '\'' +
                            ", status='" + status + '\'' +
                            '}';
                }
            }
        }
    }
}
