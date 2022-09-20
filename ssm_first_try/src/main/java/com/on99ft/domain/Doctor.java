package com.on99ft.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName(value = "doctor_info")
public class Doctor {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String name;
    private String office;
    private String skill;

    private String info;

    @TableField(exist = false)
    private String[] morning;
    @TableField(exist = false)
    private String[] afternoon;
    @TableField(exist = false)
    private String[] night;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", office='" + office + '\'' +
                ", skill='" + skill + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String[] getMorning() {
        return morning;
    }

    public void setMorning(String[] morning) {
        this.morning = morning;
    }

    public String[] getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String[] afternoon) {
        this.afternoon = afternoon;
    }

    public String[] getNight() {
        return night;
    }

    public void setNight(String[] night) {
        this.night = night;
    }
}
