package com.on99ft.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;


//todo 20220907 未来或许会计划合并doctor_info与doctor_timetable两表
@TableName(value = "doctor_timetable")
public class Dtt {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String name;
    private String morning;
    private String afternoon;
    private String night;

    @Override
    public String toString() {
        return "Dtt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", morning='" + morning + '\'' +
                ", afternoon='" + afternoon + '\'' +
                ", night='" + night + '\'' +
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

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }
}
