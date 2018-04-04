package com.cni.httptrack.resp;




/**
 * 厦门提供的接口返回数据
 * <p>
 * Created by CNI on 2018/1/18.
 */
public class CommonResponseBody<T> {

    /**
     * code : 11006
     * message : token不能为空
     * info : null
     * success : false
     */

    private String code;
    private String message;
    private T info;
    private boolean success;

    public CommonResponseBody() {
    }

    public CommonResponseBody(String code, String message, T info, boolean success) {
        this.code = code;
        this.message = message;
        this.info = info;
        this.success = success;
    }


    public static CommonResponseBody build(String code, String message, Object info, boolean success) {
        return new CommonResponseBody<>(code, message, info, success);
    }

    public static CommonResponseBody success() {
        return new CommonResponseBody<>("200", "ok", null, true);
    }

    public static CommonResponseBody success(String msg) {
        return new CommonResponseBody<>("200", msg, null, true);
    }

    public static CommonResponseBody success(String msg, Object info) {
        return new CommonResponseBody<>("200", msg, info, true);
    }

    public static CommonResponseBody error(String code, String msg) {
        return new CommonResponseBody<>(code, msg, null, false);
    }

    public static CommonResponseBody error(String code, String msg, Object info) {
        return new CommonResponseBody<>(code, msg, info, false);
    }

    public static CommonResponseBody error(String msg, Throwable e) {
        return new CommonResponseBody<>("500", msg,e, false);
    }


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

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
