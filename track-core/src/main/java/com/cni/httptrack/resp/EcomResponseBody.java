package com.cni.httptrack.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 由我们修改的Ecom原生的JSON API
 * <p>
 * Created by CNI on 2018/1/23.
 */
public class EcomResponseBody {

    /**
     * code : 200
     * message : 原生API调用成功
     * info : {"list":[{"awb_number":223390321,"orderid":"211908781E12281410","actual_weight":0.173,"origin":"MUMBAI - BOP","destination":"SRINAGAR - SXD","current_location_name":"SRINAGAR - SXD","current_location_code":"SXD","customer":"SINO INDIA ETAIL PRIVATE LIMITED - 151328","consignee":"Ms. Nazim baba","pickupdate":"28-Dec-2017","status":"Delivered / Closed","tracking_status":"Delivered","reason_code":"999 - Delivered","reason_code_description":"Delivered","reason_code_number":"999","receiver":"Self:Ms. Nazim baba: Android","lat":"34.0971715","expected_date":"09-Jan-2018","last_update_date":"10-Jan-2018","last_update_datetime":"10-Jan-2018 11:56","delivery_date":"2018-01-10 11:50:00","ref_awb":"None","rts_shipment":"0","system_delivery_update":"2018-01-10 11:56:29","rts_system_delivery_status":null,"rts_reason_code_number":null,"rts_last_update":null,"pincode":"190002","city":"SRINAGAR","state":"Jammu And Kashmir","delivery_pod_image":null,"delivery_pod_signature":"http://api3.ecomexpress.in//static/lastmile//sign/2018/1/10/sign_223390321_2018011011561515565589.png","rev_pickup_signature":null,"rev_pickup_packed_image":null,"rev_pickup_open_image":null,"scans":[{"updated_on":1517452578742,"status":"Shipment delivered","reason_code":"999 - Delivered","reason_code_number":"999","scan_status":"HOLD","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Shipment out for delivery, assigned to EmployeeIshfaq BashirBhatt","reason_code":"-","reason_code_number":"006","scan_status":"OUT","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Shipment in-scan at DC","reason_code":"-","reason_code_number":"005","scan_status":"IN","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag scanned at DC","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578828,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578828,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment connected to SRINAGAR - SXD (Bag No. YS35671998)","reason_code":"-","reason_code_number":"003","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment in-scan","reason_code":"-","reason_code_number":"002","scan_status":"IN","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Failed, Road Blocked/Premise Inaccessible","reason_code":"-","reason_code_number":"1380","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Failed, Shipment Not Ready","reason_code":"-","reason_code_number":"1350","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578833,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578833,"status":"Soft data uploaded","reason_code":"-","reason_code_number":"001","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null}],"long":"74.8008126"}]}
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

    public static class InfoBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * awb_number : 223390321
             * orderid : 211908781E12281410
             * actual_weight : 0.173
             * origin : MUMBAI - BOP
             * destination : SRINAGAR - SXD
             * current_location_name : SRINAGAR - SXD
             * current_location_code : SXD
             * customer : SINO INDIA ETAIL PRIVATE LIMITED - 151328
             * consignee : Ms. Nazim baba
             * pickupdate : 28-Dec-2017
             * status : Delivered / Closed
             * tracking_status : Delivered
             * reason_code : 999 - Delivered
             * reason_code_description : Delivered
             * reason_code_number : 999
             * receiver : Self:Ms. Nazim baba: Android
             * lat : 34.0971715
             * expected_date : 09-Jan-2018
             * last_update_date : 10-Jan-2018
             * last_update_datetime : 10-Jan-2018 11:56
             * delivery_date : 2018-01-10 11:50:00
             * ref_awb : None
             * rts_shipment : 0
             * system_delivery_update : 2018-01-10 11:56:29
             * rts_system_delivery_status : null
             * rts_reason_code_number : null
             * rts_last_update : null
             * pincode : 190002
             * city : SRINAGAR
             * state : Jammu And Kashmir
             * delivery_pod_image : null
             * delivery_pod_signature : http://api3.ecomexpress.in//static/lastmile//sign/2018/1/10/sign_223390321_2018011011561515565589.png
             * rev_pickup_signature : null
             * rev_pickup_packed_image : null
             * rev_pickup_open_image : null
             * scans : [{"updated_on":1517452578742,"status":"Shipment delivered","reason_code":"999 - Delivered","reason_code_number":"999","scan_status":"HOLD","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Shipment out for delivery, assigned to EmployeeIshfaq BashirBhatt","reason_code":"-","reason_code_number":"006","scan_status":"OUT","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Shipment in-scan at DC","reason_code":"-","reason_code_number":"005","scan_status":"IN","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag scanned at DC","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"SXD","location_city":"SRINAGAR","location_type":"Service Center","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578742,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"SXH","location_city":"SRINAGAR","location_type":"Hub","city_name":"SRINAGAR","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"DEH","location_city":"DELHI","location_type":"Hub","city_name":"DELHI","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"I1H","location_city":"INDIA ONE","location_type":"Hub","city_name":"INDIA ONE","employee":null},{"updated_on":1517452578743,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578743,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578743,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"BWH","location_city":"BHIWANDI","location_type":"Hub","city_name":"BHIWANDI","employee":null},{"updated_on":1517452578828,"status":null,"reason_code":"-","reason_code_number":null,"scan_status":"HOLD","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578828,"status":"Bag connected from HUB","reason_code":"-","reason_code_number":"003","scan_status":"OUT","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Bag scanned at Hub","reason_code":"-","reason_code_number":"003","scan_status":"IN","location":"BOH","location_city":"MUMBAI","location_type":"Hub","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment connected to SRINAGAR - SXD (Bag No. YS35671998)","reason_code":"-","reason_code_number":"003","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment in-scan","reason_code":"-","reason_code_number":"002","scan_status":"IN","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Shipment Picked Up","reason_code":"-","reason_code_number":"0011","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578829,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Failed, Road Blocked/Premise Inaccessible","reason_code":"-","reason_code_number":"1380","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578830,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578831,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Failed, Shipment Not Ready","reason_code":"-","reason_code_number":"1350","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Rescheduled For Next Day","reason_code":"-","reason_code_number":"1410","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Pickup Failed, Shipment Not Handed Over","reason_code":"-","reason_code_number":"1340","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578832,"status":"Out for Pickup","reason_code":"-","reason_code_number":"1230","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578833,"status":"Pickup Assigned","reason_code":"-","reason_code_number":"1220","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null},{"updated_on":1517452578833,"status":"Soft data uploaded","reason_code":"-","reason_code_number":"001","scan_status":"HOLD","location":"BOP","location_city":"MUMBAI","location_type":"Processing Centre","city_name":"MUMBAI","employee":null}]
             * long : 74.8008126
             */

            private int awb_number;
            private String orderid;
            private double actual_weight;
            private String origin;
            private String destination;
            private String current_location_name;
            private String current_location_code;
            private String customer;
            private String consignee;
            private String pickupdate;
            private String status;
            private String tracking_status;
            private String reason_code;
            private String reason_code_description;
            private String reason_code_number;
            private String receiver;
            private String lat;
            private String expected_date;
            private String last_update_date;
            private String last_update_datetime;
            private String delivery_date;
            private String ref_awb;
            private String rts_shipment;
            private String system_delivery_update;
            private Object rts_system_delivery_status;
            private Object rts_reason_code_number;
            private Object rts_last_update;
            private String pincode;
            private String city;
            private String state;
            private Object delivery_pod_image;
            private String delivery_pod_signature;
            private Object rev_pickup_signature;
            private Object rev_pickup_packed_image;
            private Object rev_pickup_open_image;
            @JsonProperty("long")
            private String longX;
            private List<ScansBean> scans;

            public int getAwb_number() {
                return awb_number;
            }

            public void setAwb_number(int awb_number) {
                this.awb_number = awb_number;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public double getActual_weight() {
                return actual_weight;
            }

            public void setActual_weight(double actual_weight) {
                this.actual_weight = actual_weight;
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

            public String getCurrent_location_name() {
                return current_location_name;
            }

            public void setCurrent_location_name(String current_location_name) {
                this.current_location_name = current_location_name;
            }

            public String getCurrent_location_code() {
                return current_location_code;
            }

            public void setCurrent_location_code(String current_location_code) {
                this.current_location_code = current_location_code;
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

            public String getPickupdate() {
                return pickupdate;
            }

            public void setPickupdate(String pickupdate) {
                this.pickupdate = pickupdate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTracking_status() {
                return tracking_status;
            }

            public void setTracking_status(String tracking_status) {
                this.tracking_status = tracking_status;
            }

            public String getReason_code() {
                return reason_code;
            }

            public void setReason_code(String reason_code) {
                this.reason_code = reason_code;
            }

            public String getReason_code_description() {
                return reason_code_description;
            }

            public void setReason_code_description(String reason_code_description) {
                this.reason_code_description = reason_code_description;
            }

            public String getReason_code_number() {
                return reason_code_number;
            }

            public void setReason_code_number(String reason_code_number) {
                this.reason_code_number = reason_code_number;
            }

            public String getReceiver() {
                return receiver;
            }

            public void setReceiver(String receiver) {
                this.receiver = receiver;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getExpected_date() {
                return expected_date;
            }

            public void setExpected_date(String expected_date) {
                this.expected_date = expected_date;
            }

            public String getLast_update_date() {
                return last_update_date;
            }

            public void setLast_update_date(String last_update_date) {
                this.last_update_date = last_update_date;
            }

            public String getLast_update_datetime() {
                return last_update_datetime;
            }

            public void setLast_update_datetime(String last_update_datetime) {
                this.last_update_datetime = last_update_datetime;
            }

            public String getDelivery_date() {
                return delivery_date;
            }

            public void setDelivery_date(String delivery_date) {
                this.delivery_date = delivery_date;
            }

            public String getRef_awb() {
                return ref_awb;
            }

            public void setRef_awb(String ref_awb) {
                this.ref_awb = ref_awb;
            }

            public String getRts_shipment() {
                return rts_shipment;
            }

            public void setRts_shipment(String rts_shipment) {
                this.rts_shipment = rts_shipment;
            }

            public String getSystem_delivery_update() {
                return system_delivery_update;
            }

            public void setSystem_delivery_update(String system_delivery_update) {
                this.system_delivery_update = system_delivery_update;
            }

            public Object getRts_system_delivery_status() {
                return rts_system_delivery_status;
            }

            public void setRts_system_delivery_status(Object rts_system_delivery_status) {
                this.rts_system_delivery_status = rts_system_delivery_status;
            }

            public Object getRts_reason_code_number() {
                return rts_reason_code_number;
            }

            public void setRts_reason_code_number(Object rts_reason_code_number) {
                this.rts_reason_code_number = rts_reason_code_number;
            }

            public Object getRts_last_update() {
                return rts_last_update;
            }

            public void setRts_last_update(Object rts_last_update) {
                this.rts_last_update = rts_last_update;
            }

            public String getPincode() {
                return pincode;
            }

            public void setPincode(String pincode) {
                this.pincode = pincode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getDelivery_pod_image() {
                return delivery_pod_image;
            }

            public void setDelivery_pod_image(Object delivery_pod_image) {
                this.delivery_pod_image = delivery_pod_image;
            }

            public String getDelivery_pod_signature() {
                return delivery_pod_signature;
            }

            public void setDelivery_pod_signature(String delivery_pod_signature) {
                this.delivery_pod_signature = delivery_pod_signature;
            }

            public Object getRev_pickup_signature() {
                return rev_pickup_signature;
            }

            public void setRev_pickup_signature(Object rev_pickup_signature) {
                this.rev_pickup_signature = rev_pickup_signature;
            }

            public Object getRev_pickup_packed_image() {
                return rev_pickup_packed_image;
            }

            public void setRev_pickup_packed_image(Object rev_pickup_packed_image) {
                this.rev_pickup_packed_image = rev_pickup_packed_image;
            }

            public Object getRev_pickup_open_image() {
                return rev_pickup_open_image;
            }

            public void setRev_pickup_open_image(Object rev_pickup_open_image) {
                this.rev_pickup_open_image = rev_pickup_open_image;
            }

            public String getLong() {
                return longX;
            }

            public void setLong(String longX) {
                this.longX = longX;
            }

            public List<ScansBean> getScans() {
                return scans;
            }

            public void setScans(List<ScansBean> scans) {
                this.scans = scans;
            }

            public static class ScansBean {
                /**
                 * updated_on : 1517452578742
                 * status : Shipment delivered
                 * reason_code : 999 - Delivered
                 * reason_code_number : 999
                 * scan_status : HOLD
                 * location : SXD
                 * location_city : SRINAGAR
                 * location_type : Service Center
                 * city_name : SRINAGAR
                 * employee : null
                 */

                private long updated_on;
                private String status;
                private String reason_code;
                private String reason_code_number;
                private String scan_status;
                private String location;
                private String location_city;
                private String location_type;
                private String city_name;
                private Object employee;

                public long getUpdated_on() {
                    return updated_on;
                }

                public void setUpdated_on(long updated_on) {
                    this.updated_on = updated_on;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getReason_code() {
                    return reason_code;
                }

                public void setReason_code(String reason_code) {
                    this.reason_code = reason_code;
                }

                public String getReason_code_number() {
                    return reason_code_number;
                }

                public void setReason_code_number(String reason_code_number) {
                    this.reason_code_number = reason_code_number;
                }

                public String getScan_status() {
                    return scan_status;
                }

                public void setScan_status(String scan_status) {
                    this.scan_status = scan_status;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getLocation_city() {
                    return location_city;
                }

                public void setLocation_city(String location_city) {
                    this.location_city = location_city;
                }

                public String getLocation_type() {
                    return location_type;
                }

                public void setLocation_type(String location_type) {
                    this.location_type = location_type;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public Object getEmployee() {
                    return employee;
                }

                public void setEmployee(Object employee) {
                    this.employee = employee;
                }
            }
        }
    }
}
