package com.tothecloud.offices.domain.canal;

public class SqlType {
    private int id;
    private int commodity_name;
    private int commodity_price;
    private int number;
    private int description;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setCommodity_name(int commodity_name) {
        this.commodity_name = commodity_name;
    }
    public int getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_price(int commodity_price) {
        this.commodity_price = commodity_price;
    }
    public int getCommodity_price() {
        return commodity_price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void setDescription(int description) {
        this.description = description;
    }
    public int getDescription() {
        return description;
    }
}
