package com.on99ft.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "uId")
    private Long uId;

    @TableField("uSchool")
    private String uSchool;
    @TableField("uName")
    private String uName;
    @TableField(value = "uPw",select = false)
    private String uPw;
    @TableField("uDepartment")
    private String uDepartment;

    @TableField(select = false)
    private Integer status;

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uSchool='" + uSchool + '\'' +
                ", uName='" + uName + '\'' +
                ", uPw='" + uPw + '\'' +
                ", uDepartment='" + uDepartment + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getuSchool() {
        return uSchool;
    }

    public void setuSchool(String uSchool) {
        this.uSchool = uSchool;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPw() {
        return uPw;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw;
    }

    public String getuDepartment() {
        return uDepartment;
    }

    public void setuDepartment(String uDepartment) {
        this.uDepartment = uDepartment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
