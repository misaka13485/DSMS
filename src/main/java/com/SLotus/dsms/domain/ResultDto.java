package com.SLotus.dsms.domain;


/**
 * 统一返回结果包装类
 */
public class ResultDto<T> {
    private int code;
    private String msg;
    private T data;

    public ResultDto() {
    }

    public ResultDto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功返回
     *
     * @param data 数据
     */
    public ResultDto(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 发生异常返回
     *
     * @return
     */
    public ResultDto(Exception e, String msg) {
        this.code = 500;
        this.msg = msg + e.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
