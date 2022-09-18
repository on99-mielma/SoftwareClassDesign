package com.on99ft.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.util.pattern.PathPattern;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

@TableName("offices")
public class Offices {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @TableField(value = "officeName")
    private String officeName;
    //todo 疑点1：这里需要Doctor类来描述doctor or doctor id 而不是 String 伏笔 以后改
    //思来想去我决定暂时只有name和info
    //private String doctor;
    @TableField(value = "officeInfo")
    private String officeInfo;

    @TableField(exist = false)
    private Map<Integer, List<Doctor>> DoctorInOffice;//0-6早上 10-16下午 20-26晚上

    public Map<Integer, List<Doctor>> getDoctorInOffice() {
        return DoctorInOffice;
    }

    public void setDoctorInOffice(Map<Integer, List<Doctor>> doctorInOffice) {
        DoctorInOffice = doctorInOffice;
    }

    @Override
    public String toString() {
        return "Offices{" +
                "id=" + id +
                ", officeName='" + officeName + '\'' +
                ", officeInfo='" + officeInfo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeInfo() {
        return officeInfo;
    }

    public void setOfficeInfo(String officeInfo) {
        this.officeInfo = officeInfo;
    }
}
