package com.stubhub.common;

public class Response<T> {

    private int status;

    private String message;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data, String msg) {
        Response res = new Response();
        res.setData(data);
        res.setMessage(msg);
        res.setStatus(200);
        return res;
    }

    public static <T> Response<T> failed( String msg) {
        Response res = new Response();
        res.setData(null);
        res.setMessage(msg);
        res.setStatus(500);
        return res;
    }
}
