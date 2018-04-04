package com.cni.httptrack.resp;

import java.util.List;

/**
 * Neoman钮门公司提供的格式超差的XML接口
 * <p>
 * Created by CNI on 2018/1/16.
 */
public class NeomanResponseBody {


    /**
     * code : 200
     * message : 原生API调用成功
     * info : {"statusCode":100,"emsInfo":{"referNumber":"405-1057518-4132325","item":"1","number":"EQ949044409IN","from":"Shenzhen,Guangdong","destination":"India","state":"3","sign":"OK.","trackingNumber":"EQ949044409IN","weight":"0.040","date":"2017.12.13 18:06"},"trackData":[{"dateTime":"2017-12-07 16:16","place":"International Fulfillment Center","info":"Check In Scan"},{"dateTime":"2017-12-09 16:48","place":"International Fulfillment Center","info":"Package Dispatched"},{"dateTime":"2017-12-09 16:07","place":"S.J. SORTING","info":"Item Booked"},{"dateTime":"2017-12-11 17:05","place":"S.J. SORTING","info":"Item Bagged for NSH NAGPUR"},{"dateTime":"2017-12-11 17:55","place":"S.J. SORTING","info":"Bag Despatched to PALAM NEW DELHI"},{"dateTime":"2017-12-11 19:34","place":"PALAM TMO","info":"Bag Received"},{"dateTime":"2017-12-11 23:54","place":"PALAM TMO","info":"Bag Despatched to NSH NAGPUR"},{"dateTime":"2017-12-12 10:44","place":"NSH NAGPUR","info":"Bag Received"},{"dateTime":"2017-12-12 10:46","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:46","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 15:26","place":"NSH NAGPUR","info":"Item Bagged for Arvi S.O (Wardha)"},{"dateTime":"2017-12-13 02:44","place":"NSH NAGPUR","info":"Bag Despatched to WARDHA R.M.S."},{"dateTime":"2017-12-13 10:18","place":"Arvi S.O (Wardha)","info":"Bag Received"},{"dateTime":"2017-12-13 10:19","place":"Arvi S.O (Wardha)","info":"Bag Opened"},{"dateTime":"2017-12-13 10:19","place":"Arvi S.O (Wardha)","info":"Item Received"},{"dateTime":"2017-12-13 18:06","place":"Arvi S.O (Wardha)","info":"Item delivered"}],"success":true}
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
        return "NeomanResponseBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", info=" + info +
                ", success=" + success +
                '}';
    }

    public static class InfoBean {
        /**
         * statusCode : 100
         * emsInfo : {"referNumber":"405-1057518-4132325","item":"1","number":"EQ949044409IN","from":"Shenzhen,Guangdong","destination":"India","state":"3","sign":"OK.","trackingNumber":"EQ949044409IN","weight":"0.040","date":"2017.12.13 18:06"}
         * trackData : [{"dateTime":"2017-12-07 16:16","place":"International Fulfillment Center","info":"Check In Scan"},{"dateTime":"2017-12-09 16:48","place":"International Fulfillment Center","info":"Package Dispatched"},{"dateTime":"2017-12-09 16:07","place":"S.J. SORTING","info":"Item Booked"},{"dateTime":"2017-12-11 17:05","place":"S.J. SORTING","info":"Item Bagged for NSH NAGPUR"},{"dateTime":"2017-12-11 17:55","place":"S.J. SORTING","info":"Bag Despatched to PALAM NEW DELHI"},{"dateTime":"2017-12-11 19:34","place":"PALAM TMO","info":"Bag Received"},{"dateTime":"2017-12-11 23:54","place":"PALAM TMO","info":"Bag Despatched to NSH NAGPUR"},{"dateTime":"2017-12-12 10:44","place":"NSH NAGPUR","info":"Bag Received"},{"dateTime":"2017-12-12 10:46","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:46","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Bag Opened"},{"dateTime":"2017-12-12 10:47","place":"NSH NAGPUR","info":"Item Received"},{"dateTime":"2017-12-12 15:26","place":"NSH NAGPUR","info":"Item Bagged for Arvi S.O (Wardha)"},{"dateTime":"2017-12-13 02:44","place":"NSH NAGPUR","info":"Bag Despatched to WARDHA R.M.S."},{"dateTime":"2017-12-13 10:18","place":"Arvi S.O (Wardha)","info":"Bag Received"},{"dateTime":"2017-12-13 10:19","place":"Arvi S.O (Wardha)","info":"Bag Opened"},{"dateTime":"2017-12-13 10:19","place":"Arvi S.O (Wardha)","info":"Item Received"},{"dateTime":"2017-12-13 18:06","place":"Arvi S.O (Wardha)","info":"Item delivered"}]
         * success : true
         */

        private int statusCode;
        private EmsInfoBean emsInfo;
        private boolean success;
        private List<TrackDataBean> trackData;

        @Override
        public String toString() {
            return "InfoBean{" +
                    "statusCode=" + statusCode +
                    ", emsInfo=" + emsInfo +
                    ", success=" + success +
                    ", trackData=" + trackData +
                    '}';
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public EmsInfoBean getEmsInfo() {
            return emsInfo;
        }

        public void setEmsInfo(EmsInfoBean emsInfo) {
            this.emsInfo = emsInfo;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public List<TrackDataBean> getTrackData() {
            return trackData;
        }

        public void setTrackData(List<TrackDataBean> trackData) {
            this.trackData = trackData;
        }

        public static class EmsInfoBean {
            /**
             * referNumber : 405-1057518-4132325
             * item : 1
             * number : EQ949044409IN
             * from : Shenzhen,Guangdong
             * destination : India
             * state : 3
             * sign : OK.
             * trackingNumber : EQ949044409IN
             * weight : 0.040
             * date : 2017.12.13 18:06
             */

            private String referNumber;
            private String item;
            private String number;
            private String from;
            private String destination;
            private String state;
            private String sign;
            private String trackingNumber;
            private String weight;
            private String date;

            public String getReferNumber() {
                return referNumber;
            }

            public void setReferNumber(String referNumber) {
                this.referNumber = referNumber;
            }

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getTrackingNumber() {
                return trackingNumber;
            }

            public void setTrackingNumber(String trackingNumber) {
                this.trackingNumber = trackingNumber;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            @Override
            public String toString() {
                return "EmsInfoBean{" +
                        "referNumber='" + referNumber + '\'' +
                        ", item='" + item + '\'' +
                        ", number='" + number + '\'' +
                        ", from='" + from + '\'' +
                        ", destination='" + destination + '\'' +
                        ", state='" + state + '\'' +
                        ", sign='" + sign + '\'' +
                        ", trackingNumber='" + trackingNumber + '\'' +
                        ", weight='" + weight + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }
        }

        public static class TrackDataBean {
            /**
             * dateTime : 2017-12-07 16:16
             * place : International Fulfillment Center
             * info : Check In Scan
             */

            private String dateTime;
            private String place;
            private String info;


            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            @Override
            public String toString() {
                return "TrackDataBean{" +
                        "dateTime='" + dateTime + '\'' +
                        ", place='" + place + '\'' +
                        ", info='" + info + '\'' +
                        '}';
            }
        }
    }
}
