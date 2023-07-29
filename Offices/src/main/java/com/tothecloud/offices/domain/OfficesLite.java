package com.tothecloud.offices.domain;

public class OfficesLite {
    private String Officename;


    public OfficesLite(Office office) {
        this(office.getOfficeName());
//        assert null!=office.getOfficeName() : "Office中的属性不能为null";
    }

    public OfficesLite(String officename) {
        assert null != officename : "officename can not be null";
        Officename = officename;
    }

    public OfficesLite() {
    }

    public String getOfficename() {
        return Officename;
    }

    public void setOfficename(String officename) {
        Officename = officename;
    }

    @Override
    public String toString() {
        return "OfficesLite{" +
                "Officename='" + Officename + '\'' +
                '}';
    }

}
