package com.ee.enigma.dto;

import java.util.Date;

public class ReportInfo {
    String activityId;
    String loginTime;
    String logoutTime;

    Date inDate;
    Date outDate;

    String location;
    String userId;
    String issueId;
    String deviceId;
    String fromTable;
    String userName;
    String deviceName;
    private Boolean issueByAdmin;
    private Boolean submitByAdmin;
    private String deviceAvailability;
    private String manufacturer;
    private String os;
    private String osVersion;
    private String issuedBy;
    private String yearOfManufacturing;

    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String oS) {
        this.os = oS;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String oSVersion) {
        this.osVersion = oSVersion;
    }

    public Boolean getIssueByAdmin() {
        return issueByAdmin;
    }

    public void setIssueByAdmin(Boolean issueByAdmin) {
        this.issueByAdmin = issueByAdmin;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFromTable() {
        return fromTable;
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceAvailability() {
        return deviceAvailability;
    }

    public void setDeviceAvailability(String deviceAvailability) {
        this.deviceAvailability = deviceAvailability;
    }

    public Boolean getSubmitByAdmin() {
        return submitByAdmin;
    }

    public void setSubmitByAdmin(Boolean submitByAdmin) {
        this.submitByAdmin = submitByAdmin;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

}
