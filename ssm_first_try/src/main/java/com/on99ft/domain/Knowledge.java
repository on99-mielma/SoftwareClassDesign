package com.on99ft.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

//todo 实验性类 未来删除
public class Knowledge {
    // TODO: 2022/8/31 精度问题 Long值类型传到前端最后两位数字会变成0 已解决 
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String text;

    private String title;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime date;


    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
