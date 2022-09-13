package com.on99ft.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.BlobTypeHandler;

import java.util.Arrays;

@TableName(value = "resume",autoResultMap = true)
public class Resume {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String name;
    private String phone_number;
    private String gender;
    private String card_number;
    //todo https://blog.csdn.net/qq_28169023/article/details/125367483?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EESLANDING%7Edefault-1-125367483-blog-114965325.relrec_prioritylanding&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EESLANDING%7Edefault-1-125367483-blog-114965325.relrec_prioritylanding&utm_relevant_index=1
    @TableField(value = "file",typeHandler = BlobTypeHandler.class)
    private byte[] file;

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", gender='" + gender + '\'' +
                ", card_number='" + card_number + '\'' +
                ", file=" + Arrays.toString(file) +
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
