package com.maple.bbs.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

public class Result {


    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Result resultMessage(Integer status,String message){
        return new Result(status,message);
    }

    public static Result resultData(Integer status,String message,Object data){
        return new Result(status,message,data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
