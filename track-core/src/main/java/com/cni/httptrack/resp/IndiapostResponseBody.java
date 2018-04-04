package com.cni.httptrack.resp;

import java.util.List;

/**
 * 厦门提供的接口查询IndiaPost返回的响应数据
 *
 * Created by CNI on 2018/1/18.
 */
public class IndiapostResponseBody {


    /**
     * code : 200
     * message : null
     * info : {"awb":"EQ949022774IN","bookAt":"S.J. SORTING","bookOn":"14/11/2017","destinationPincode":"422005","tariff":"94.00","articleType":"SPA","deliveredAt":"Gurgaon Road S.O","deliveredOn":"27/11/2017","details":[{"date":"27/11/2017","time":"15:52:00","office":"Gurgaon Road S.O","event":"Item delivered [To: PRIME ]"},{"date":"27/11/2017","time":"08:15:53","office":"Gurgaon Road S.O","event":"Item Received"},{"date":"27/11/2017","time":"08:15:53","office":"Gurgaon Road S.O","event":"Bag Opened"},{"date":"27/11/2017","time":"07:55:16","office":"Gurgaon Road S.O","event":"Bag Received"},{"date":"26/11/2017","time":"06:24:50","office":"NSH NEW DELHI","event":"Bag Despatched to Gurgaon Road S.O"},{"date":"26/11/2017","time":"05:11:07","office":"NSH NEW DELHI","event":"Item Bagged for Gurgaon Road S.O"},{"date":"26/11/2017","time":"02:06:50","office":"NSH NEW DELHI","event":"Item Received"},{"date":"26/11/2017","time":"00:52:15","office":"NSH NEW DELHI","event":"Bag Received"},{"date":"25/11/2017","time":"23:44:36","office":"PALAM TMO","event":"Bag Despatched to NSH NEW DELHI"},{"date":"25/11/2017","time":"23:14:10","office":"PALAM TMO","event":"Bag Received"},{"date":"25/11/2017","time":"12:51:45","office":"NSH MUMBAI","event":"Bag Despatched to NSH NEW DELHI"},{"date":"25/11/2017","time":"11:00:16","office":"NSH MUMBAI","event":"Item Bagged for NSH NEW DELHI"},{"date":"25/11/2017","time":"09:07:18","office":"NSH MUMBAI","event":"Item Received"},{"date":"25/11/2017","time":"09:07:18","office":"NSH MUMBAI","event":"Bag Opened"},{"date":"25/11/2017","time":"08:45:49","office":"NSH MUMBAI","event":"Bag Received"},{"date":"25/11/2017","time":"02:58:13","office":"ICH NASHIK","event":"Bag Despatched to Nashik Road RS TMO"},{"date":"24/11/2017","time":"20:17:12","office":"ICH NASHIK","event":"Item Bagged for NSH MUMBAI"},{"date":"24/11/2017","time":"20:08:48","office":"ICH NASHIK","event":"Item Received"},{"date":"24/11/2017","time":"20:08:48","office":"ICH NASHIK","event":"Bag Opened"},{"date":"24/11/2017","time":"20:08:37","office":"ICH NASHIK","event":"Bag Received"},{"date":"24/11/2017","time":"15:19:31","office":"H P T College S.O","event":"Bag Despatched to ICH NASHIK"},{"date":"24/11/2017","time":"15:10:12","office":"H P T College S.O","event":"Item Bagged for ICH NASHIK"},{"date":"24/11/2017","time":"12:33:00","office":"H P T College S.O","event":"Item delivery attempted Unclaimed"},{"date":"17/11/2017","time":"10:08:28","office":"H P T College S.O","event":"Item Received"},{"date":"17/11/2017","time":"04:33:17","office":"ICH NASHIK","event":"Item Bagged for H P T College S.O"},{"date":"17/11/2017","time":"02:21:23","office":"ICH NASHIK","event":"Item Received"},{"date":"16/11/2017","time":"03:57:52","office":"NSH MUMBAI","event":"Item Bagged for ICH NASHIK"},{"date":"16/11/2017","time":"00:30:10","office":"NSH MUMBAI","event":"Item Received"},{"date":"14/11/2017","time":"17:12:52","office":"S.J. SORTING","event":"Item Bagged for NSH MUMBAI"},{"date":"14/11/2017","time":"15:40:24","office":"S.J. SORTING","event":"Item Booked"}],"tryTimes":3,"pageNum":2}
     * success : true
     */

    private String code;
    private Object message;
    private InfoBean info;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
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
        return "IndiapostResponseBody{" +
                "code='" + code + '\'' +
                ", message=" + message +
                ", info=" + info +
                ", success=" + success +
                '}';
    }

    public static class InfoBean {
        /**
         * awb : EQ949022774IN
         * bookAt : S.J. SORTING
         * bookOn : 14/11/2017
         * destinationPincode : 422005
         * tariff : 94.00
         * articleType : SPA
         * deliveredAt : Gurgaon Road S.O
         * deliveredOn : 27/11/2017
         * details : [{"date":"27/11/2017","time":"15:52:00","office":"Gurgaon Road S.O","event":"Item delivered [To: PRIME ]"},{"date":"27/11/2017","time":"08:15:53","office":"Gurgaon Road S.O","event":"Item Received"},{"date":"27/11/2017","time":"08:15:53","office":"Gurgaon Road S.O","event":"Bag Opened"},{"date":"27/11/2017","time":"07:55:16","office":"Gurgaon Road S.O","event":"Bag Received"},{"date":"26/11/2017","time":"06:24:50","office":"NSH NEW DELHI","event":"Bag Despatched to Gurgaon Road S.O"},{"date":"26/11/2017","time":"05:11:07","office":"NSH NEW DELHI","event":"Item Bagged for Gurgaon Road S.O"},{"date":"26/11/2017","time":"02:06:50","office":"NSH NEW DELHI","event":"Item Received"},{"date":"26/11/2017","time":"00:52:15","office":"NSH NEW DELHI","event":"Bag Received"},{"date":"25/11/2017","time":"23:44:36","office":"PALAM TMO","event":"Bag Despatched to NSH NEW DELHI"},{"date":"25/11/2017","time":"23:14:10","office":"PALAM TMO","event":"Bag Received"},{"date":"25/11/2017","time":"12:51:45","office":"NSH MUMBAI","event":"Bag Despatched to NSH NEW DELHI"},{"date":"25/11/2017","time":"11:00:16","office":"NSH MUMBAI","event":"Item Bagged for NSH NEW DELHI"},{"date":"25/11/2017","time":"09:07:18","office":"NSH MUMBAI","event":"Item Received"},{"date":"25/11/2017","time":"09:07:18","office":"NSH MUMBAI","event":"Bag Opened"},{"date":"25/11/2017","time":"08:45:49","office":"NSH MUMBAI","event":"Bag Received"},{"date":"25/11/2017","time":"02:58:13","office":"ICH NASHIK","event":"Bag Despatched to Nashik Road RS TMO"},{"date":"24/11/2017","time":"20:17:12","office":"ICH NASHIK","event":"Item Bagged for NSH MUMBAI"},{"date":"24/11/2017","time":"20:08:48","office":"ICH NASHIK","event":"Item Received"},{"date":"24/11/2017","time":"20:08:48","office":"ICH NASHIK","event":"Bag Opened"},{"date":"24/11/2017","time":"20:08:37","office":"ICH NASHIK","event":"Bag Received"},{"date":"24/11/2017","time":"15:19:31","office":"H P T College S.O","event":"Bag Despatched to ICH NASHIK"},{"date":"24/11/2017","time":"15:10:12","office":"H P T College S.O","event":"Item Bagged for ICH NASHIK"},{"date":"24/11/2017","time":"12:33:00","office":"H P T College S.O","event":"Item delivery attempted Unclaimed"},{"date":"17/11/2017","time":"10:08:28","office":"H P T College S.O","event":"Item Received"},{"date":"17/11/2017","time":"04:33:17","office":"ICH NASHIK","event":"Item Bagged for H P T College S.O"},{"date":"17/11/2017","time":"02:21:23","office":"ICH NASHIK","event":"Item Received"},{"date":"16/11/2017","time":"03:57:52","office":"NSH MUMBAI","event":"Item Bagged for ICH NASHIK"},{"date":"16/11/2017","time":"00:30:10","office":"NSH MUMBAI","event":"Item Received"},{"date":"14/11/2017","time":"17:12:52","office":"S.J. SORTING","event":"Item Bagged for NSH MUMBAI"},{"date":"14/11/2017","time":"15:40:24","office":"S.J. SORTING","event":"Item Booked"}]
         * tryTimes : 3
         * pageNum : 2
         */

        private String awb;
        private String bookAt;
        private String bookOn;
        private String destinationPincode;
        private String tariff;
        private String articleType;
        private String deliveredAt;
        private String deliveredOn;
        private int tryTimes;
        private int pageNum;
        private List<DetailsBean> details;

        public String getAwb() {
            return awb;
        }

        public void setAwb(String awb) {
            this.awb = awb;
        }

        public String getBookAt() {
            return bookAt;
        }

        public void setBookAt(String bookAt) {
            this.bookAt = bookAt;
        }

        public String getBookOn() {
            return bookOn;
        }

        public void setBookOn(String bookOn) {
            this.bookOn = bookOn;
        }

        public String getDestinationPincode() {
            return destinationPincode;
        }

        public void setDestinationPincode(String destinationPincode) {
            this.destinationPincode = destinationPincode;
        }

        public String getTariff() {
            return tariff;
        }

        public void setTariff(String tariff) {
            this.tariff = tariff;
        }

        public String getArticleType() {
            return articleType;
        }

        public void setArticleType(String articleType) {
            this.articleType = articleType;
        }

        public String getDeliveredAt() {
            return deliveredAt;
        }

        public void setDeliveredAt(String deliveredAt) {
            this.deliveredAt = deliveredAt;
        }

        public String getDeliveredOn() {
            return deliveredOn;
        }

        public void setDeliveredOn(String deliveredOn) {
            this.deliveredOn = deliveredOn;
        }

        public int getTryTimes() {
            return tryTimes;
        }

        public void setTryTimes(int tryTimes) {
            this.tryTimes = tryTimes;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return "InfoBean{" +
                    "awb='" + awb + '\'' +
                    ", bookAt='" + bookAt + '\'' +
                    ", bookOn='" + bookOn + '\'' +
                    ", destinationPincode='" + destinationPincode + '\'' +
                    ", tariff='" + tariff + '\'' +
                    ", articleType='" + articleType + '\'' +
                    ", deliveredAt='" + deliveredAt + '\'' +
                    ", deliveredOn='" + deliveredOn + '\'' +
                    ", tryTimes=" + tryTimes +
                    ", pageNum=" + pageNum +
                    ", details=" + details +
                    '}';
        }

        public static class DetailsBean {
            /**
             * date : 27/11/2017
             * time : 15:52:00
             * office : Gurgaon Road S.O
             * event : Item delivered [To: PRIME ]
             */

            private String date;
            private String time;
            private String office;
            private String event;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getOffice() {
                return office;
            }

            public void setOffice(String office) {
                this.office = office;
            }

            public String getEvent() {
                return event;
            }

            public void setEvent(String event) {
                this.event = event;
            }

            @Override
            public String toString() {
                return "DetailsBean{" +
                        "date='" + date + '\'' +
                        ", time='" + time + '\'' +
                        ", office='" + office + '\'' +
                        ", event='" + event + '\'' +
                        '}';
            }
        }
    }
}
