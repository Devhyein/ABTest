package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testdata")
public class TestData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "data_no")
    private int dataNo;

    @Column(name = "url_no")
    private int urlNo;

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "user_no")
    private int userNo;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "isJoined")
    private boolean isJoined;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    public TestData() {
    }

    public TestData(int dataNo, int urlNo, int testNo, int userNo, String pageType, LocalDate date, boolean isJoined,
            String gender, int age) {
        this.dataNo = dataNo;
        this.urlNo = urlNo;
        this.testNo = testNo;
        this.userNo = userNo;
        this.pageType = pageType;
        this.date = date;
        this.isJoined = isJoined;
        this.gender = gender;
        this.age = age;
    }

    public int getDataNo() {
        return dataNo;
    }

    public void setDataNo(int dataNo) {
        this.dataNo = dataNo;
    }

    public int getUrlNo() {
        return urlNo;
    }

    public void setUrlNo(int urlNo) {
        this.urlNo = urlNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean isJoined) {
        this.isJoined = isJoined;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestData [age=" + age + ", dataNo=" + dataNo + ", date=" + date + ", gender=" + gender + ", isJoined="
                + isJoined + ", pageType=" + pageType + ", testNo=" + testNo + ", urlNo=" + urlNo + ", userNo=" + userNo
                + "]";
    }

}
