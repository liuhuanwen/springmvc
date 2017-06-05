package com.liuhw.springmvc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by liuhw on 2017/6/4.
 */
public class Response<T> {

    private int status;
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private T t;
    private String msg;

    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Response(int status, T t, String msg) {
        this.status = status;
        this.t = t;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
