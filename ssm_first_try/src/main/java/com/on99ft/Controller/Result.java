package com.on99ft.Controller;

public class Result {
    private Integer code;
    private String msg;
    private Object data;


    private Integer id;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }



    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public Result(Integer code, String msg, Object data , Integer id) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.id = id;
    }
    //重载，java不支持形参默认，id用来判断表格显示哪个,id为0是医生，1是科室
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.id = 0;
    }
}
