package com.cni.httptrack.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Gati接口
 * <p>
 * Created by CNI on 2018/1/23.
 */
public class GatiResponseBody {

    private String code;
    private String message;
    private GatiInnerBean info;
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

    public GatiInnerBean getInfo() {
        return info;
    }

    public void setInfo(GatiInnerBean info) {
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
        return "GatiResponseBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", info=" + info +
                ", success=" + success +
                '}';
    }

    public static class GatiInnerBean {
        /**
         * code : 0000
         * msg : 成功
         * list : [{"ResultMsg":"SUCCESS","Data":{"EmsInfo":{"TDate":"","Des":"印度","StateDesc":"送达","State":"3","Sign":"nishanth daniel","From":"","ADate":"2017.11.26 13:34","TrckingNbr":"661086868"},"TrackData":[{"dateTime":"2017-11-26 13:34","place":"Hyderabad","info":"Delivered. Signed for by nishanth daniel","compareDateTime":1511674440000},{"dateTime":"2017-11-26 07:26","place":"Hyderabad Central","info":"Arrived","compareDateTime":1511652360000},{"dateTime":"2017-11-26 06:06","place":"Hyderabad Outbound","info":"Out for next station","compareDateTime":1511647560000},{"dateTime":"2017-11-25 21:26","place":"Hyderabad Outbound","info":"Arrived","compareDateTime":1511616360000},{"dateTime":"2017-11-25 20:31","place":"Hyderabad Tarbund (Ecom)","info":"Out for next station","compareDateTime":1511613060000},{"dateTime":"2017-11-25 16:52","place":"Hyderabad Tarbund (Ecom)","info":"Arrived","compareDateTime":1511599920000},{"dateTime":"2017-11-25 07:29","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511566140000},{"dateTime":"2017-11-25 06:54","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511564040000},{"dateTime":"2017-11-25 05:08","place":"Delhi Atc","info":"Arrived","compareDateTime":1511557680000},{"dateTime":"2017-11-24 20:07","place":"Delhi Gate Way","info":"Out for next station","compareDateTime":1511525220000},{"dateTime":"2017-11-24 14:40","place":"Delhi Gate Way","info":"Arrived","compareDateTime":1511505600000},{"dateTime":"2017-11-22 17:48","place":"International Operation Center","info":"In Transit","compareDateTime":1511344080000},{"dateTime":"2017-11-22 15:28","place":"Shanghai","info":"Out for next station","compareDateTime":1511335680000},{"dateTime":"2017-11-22 14:53","place":"International Operation Center","info":"Check in","compareDateTime":1511333580000}]},"CustomerRef":"9854065","TrackNumber":"661086868","ResultCode":"0000"}]
         */

        @JsonProperty("code")
        private String code;
        @JsonProperty("msg")
        private String msg;
        @JsonProperty("list")
        private List<ListBean> list;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "GatiResponseBody{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    ", list=" + list +
                    '}';
        }

        public static class ListBean {
            /**
             * ResultMsg : SUCCESS
             * Data : {"EmsInfo":{"TDate":"","Des":"印度","StateDesc":"送达","State":"3","Sign":"nishanth daniel","From":"","ADate":"2017.11.26 13:34","TrckingNbr":"661086868"},"TrackData":[{"dateTime":"2017-11-26 13:34","place":"Hyderabad","info":"Delivered. Signed for by nishanth daniel","compareDateTime":1511674440000},{"dateTime":"2017-11-26 07:26","place":"Hyderabad Central","info":"Arrived","compareDateTime":1511652360000},{"dateTime":"2017-11-26 06:06","place":"Hyderabad Outbound","info":"Out for next station","compareDateTime":1511647560000},{"dateTime":"2017-11-25 21:26","place":"Hyderabad Outbound","info":"Arrived","compareDateTime":1511616360000},{"dateTime":"2017-11-25 20:31","place":"Hyderabad Tarbund (Ecom)","info":"Out for next station","compareDateTime":1511613060000},{"dateTime":"2017-11-25 16:52","place":"Hyderabad Tarbund (Ecom)","info":"Arrived","compareDateTime":1511599920000},{"dateTime":"2017-11-25 07:29","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511566140000},{"dateTime":"2017-11-25 06:54","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511564040000},{"dateTime":"2017-11-25 05:08","place":"Delhi Atc","info":"Arrived","compareDateTime":1511557680000},{"dateTime":"2017-11-24 20:07","place":"Delhi Gate Way","info":"Out for next station","compareDateTime":1511525220000},{"dateTime":"2017-11-24 14:40","place":"Delhi Gate Way","info":"Arrived","compareDateTime":1511505600000},{"dateTime":"2017-11-22 17:48","place":"International Operation Center","info":"In Transit","compareDateTime":1511344080000},{"dateTime":"2017-11-22 15:28","place":"Shanghai","info":"Out for next station","compareDateTime":1511335680000},{"dateTime":"2017-11-22 14:53","place":"International Operation Center","info":"Check in","compareDateTime":1511333580000}]}
             * CustomerRef : 9854065
             * TrackNumber : 661086868
             * ResultCode : 0000
             */

            @JsonProperty("ResultMsg")
            private String ResultMsg;
            @JsonProperty("Data")
            private DataBean Data;
            @JsonProperty("CustomerRef")
            private String CustomerRef;
            @JsonProperty("TrackNumber")
            private String TrackNumber;
            @JsonProperty("ResultCode")
            private String ResultCode;

            public String getResultMsg() {
                return ResultMsg;
            }

            public void setResultMsg(String ResultMsg) {
                this.ResultMsg = ResultMsg;
            }

            public DataBean getData() {
                return Data;
            }

            public void setData(DataBean Data) {
                this.Data = Data;
            }

            public String getCustomerRef() {
                return CustomerRef;
            }

            public void setCustomerRef(String CustomerRef) {
                this.CustomerRef = CustomerRef;
            }

            public String getTrackNumber() {
                return TrackNumber;
            }

            public void setTrackNumber(String TrackNumber) {
                this.TrackNumber = TrackNumber;
            }

            public String getResultCode() {
                return ResultCode;
            }

            public void setResultCode(String ResultCode) {
                this.ResultCode = ResultCode;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "ResultMsg='" + ResultMsg + '\'' +
                        ", Data=" + Data +
                        ", CustomerRef='" + CustomerRef + '\'' +
                        ", TrackNumber='" + TrackNumber + '\'' +
                        ", ResultCode='" + ResultCode + '\'' +
                        '}';
            }

            public static class DataBean {
                /**
                 * EmsInfo : {"TDate":"","Des":"印度","StateDesc":"送达","State":"3","Sign":"nishanth daniel","From":"","ADate":"2017.11.26 13:34","TrckingNbr":"661086868"}
                 * TrackData : [{"dateTime":"2017-11-26 13:34","place":"Hyderabad","info":"Delivered. Signed for by nishanth daniel","compareDateTime":1511674440000},{"dateTime":"2017-11-26 07:26","place":"Hyderabad Central","info":"Arrived","compareDateTime":1511652360000},{"dateTime":"2017-11-26 06:06","place":"Hyderabad Outbound","info":"Out for next station","compareDateTime":1511647560000},{"dateTime":"2017-11-25 21:26","place":"Hyderabad Outbound","info":"Arrived","compareDateTime":1511616360000},{"dateTime":"2017-11-25 20:31","place":"Hyderabad Tarbund (Ecom)","info":"Out for next station","compareDateTime":1511613060000},{"dateTime":"2017-11-25 16:52","place":"Hyderabad Tarbund (Ecom)","info":"Arrived","compareDateTime":1511599920000},{"dateTime":"2017-11-25 07:29","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511566140000},{"dateTime":"2017-11-25 06:54","place":"Delhi Atc","info":"Out for next station","compareDateTime":1511564040000},{"dateTime":"2017-11-25 05:08","place":"Delhi Atc","info":"Arrived","compareDateTime":1511557680000},{"dateTime":"2017-11-24 20:07","place":"Delhi Gate Way","info":"Out for next station","compareDateTime":1511525220000},{"dateTime":"2017-11-24 14:40","place":"Delhi Gate Way","info":"Arrived","compareDateTime":1511505600000},{"dateTime":"2017-11-22 17:48","place":"International Operation Center","info":"In Transit","compareDateTime":1511344080000},{"dateTime":"2017-11-22 15:28","place":"Shanghai","info":"Out for next station","compareDateTime":1511335680000},{"dateTime":"2017-11-22 14:53","place":"International Operation Center","info":"Check in","compareDateTime":1511333580000}]
                 */

                @JsonProperty("EmsInfo")
                private EmsInfoBean EmsInfo;
                @JsonProperty("TrackData")
                private List<TrackDataBean> TrackData;

                public EmsInfoBean getEmsInfo() {
                    return EmsInfo;
                }

                public void setEmsInfo(EmsInfoBean EmsInfo) {
                    this.EmsInfo = EmsInfo;
                }

                public List<TrackDataBean> getTrackData() {
                    return TrackData;
                }

                public void setTrackData(List<TrackDataBean> TrackData) {
                    this.TrackData = TrackData;
                }

                @Override
                public String toString() {
                    return "DataBean{" +
                            "EmsInfo=" + EmsInfo +
                            ", TrackData=" + TrackData +
                            '}';
                }

                public static class EmsInfoBean {
                    /**
                     * TDate :
                     * Des : 印度
                     * StateDesc : 送达
                     * State : 3
                     * Sign : nishanth daniel
                     * From :
                     * ADate : 2017.11.26 13:34
                     * TrckingNbr : 661086868
                     */

                    @JsonProperty("TDate")
                    private String TDate;
                    @JsonProperty("Des")
                    private String Des;
                    @JsonProperty("StateDesc")
                    private String StateDesc;
                    @JsonProperty("State")
                    private String State;
                    @JsonProperty("Sign")
                    private String Sign;
                    @JsonProperty("From")
                    private String From;
                    @JsonProperty("ADate")
                    private String ADate;
                    @JsonProperty("TrckingNbr")
                    private String TrckingNbr;

                    public String getTDate() {
                        return TDate;
                    }

                    public void setTDate(String TDate) {
                        this.TDate = TDate;
                    }

                    public String getDes() {
                        return Des;
                    }

                    public void setDes(String Des) {
                        this.Des = Des;
                    }

                    public String getStateDesc() {
                        return StateDesc;
                    }

                    public void setStateDesc(String StateDesc) {
                        this.StateDesc = StateDesc;
                    }

                    public String getState() {
                        return State;
                    }

                    public void setState(String State) {
                        this.State = State;
                    }

                    public String getSign() {
                        return Sign;
                    }

                    public void setSign(String Sign) {
                        this.Sign = Sign;
                    }

                    public String getFrom() {
                        return From;
                    }

                    public void setFrom(String From) {
                        this.From = From;
                    }

                    public String getADate() {
                        return ADate;
                    }

                    public void setADate(String ADate) {
                        this.ADate = ADate;
                    }

                    public String getTrckingNbr() {
                        return TrckingNbr;
                    }

                    public void setTrckingNbr(String TrckingNbr) {
                        this.TrckingNbr = TrckingNbr;
                    }

                    @Override
                    public String toString() {
                        return "EmsInfoBean{" +
                                "TDate='" + TDate + '\'' +
                                ", Des='" + Des + '\'' +
                                ", StateDesc='" + StateDesc + '\'' +
                                ", State='" + State + '\'' +
                                ", Sign='" + Sign + '\'' +
                                ", From='" + From + '\'' +
                                ", ADate='" + ADate + '\'' +
                                ", TrckingNbr='" + TrckingNbr + '\'' +
                                '}';
                    }
                }

                public static class TrackDataBean {
                    /**
                     * dateTime : 2017-11-26 13:34
                     * place : Hyderabad
                     * info : Delivered. Signed for by nishanth daniel
                     * compareDateTime : 1511674440000
                     */

                    private String dateTime;
                    private String place;
                    private String info;
                    private long compareDateTime;

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

                    public long getCompareDateTime() {
                        return compareDateTime;
                    }

                    public void setCompareDateTime(long compareDateTime) {
                        this.compareDateTime = compareDateTime;
                    }

                    @Override
                    public String toString() {
                        return "TrackDataBean{" +
                                "dateTime='" + dateTime + '\'' +
                                ", place='" + place + '\'' +
                                ", info='" + info + '\'' +
                                ", compareDateTime=" + compareDateTime +
                                '}';
                    }
                }
            }
        }

    }

}
