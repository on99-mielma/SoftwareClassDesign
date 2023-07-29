package com.tothecloud.offices.domain;

public class Office {
    private Long id;
    private String officeName;
    private String officeInfo;

    public Office() {
    }

    public Office(Long id, String officeName, String officeInfo) {
        this.id = id;
        this.officeName = officeName;
        this.officeInfo = officeInfo;
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

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", officeName='" + officeName + '\'' +
                ", officeInfo='" + officeInfo + '\'' +
                '}';
    }
}
