package com.pig.learn.mybatis.model;

import com.google.common.base.Objects;
import com.pig.learn.mybatis.constant.Code;

import java.io.Serializable;

/**
 * 泛型用法
 * @param <T>
 */
public class Result<T> implements Serializable {
    private T data;
    private int code;
    private String message;
    private long costTime;
    private long gmtCurrentTime;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getGmtCurrentTime() {
        return gmtCurrentTime;
    }

    public void setGmtCurrentTime(long gmtCurrentTime) {
        this.gmtCurrentTime = gmtCurrentTime;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("data", data)
                .add("code", code)
                .add("message", message)
                .add("costTime", costTime)
                .add("gmtCurrentTime", gmtCurrentTime)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return code == result.code &&
                costTime == result.costTime &&
                gmtCurrentTime == result.gmtCurrentTime &&
                Objects.equal(data, result.data) &&
                Objects.equal(message, result.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data, code, message, costTime, gmtCurrentTime);
    }

    public static void main(String[] args) {
        Result<Integer> result = new Result<>();

        result.setCode(Code.SUCCESS);
        result.setData(11);

        System.out.println(result);
    }
}
