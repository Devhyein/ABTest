package com.ssafy.free.dto.Admin;

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

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "url_no")
    private int urlNo;

    @Column(name = "user_no")
    private int userNo;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "signed")
    private boolean signed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "join_date")
    private LocalDate joinDate;

    public TestData() {
    }

    public TestData(int dataNo, int urlNo, int testNo, int userNo, String pageType, LocalDate date, boolean signed,
            String gender, int age, LocalDate joinDate) {
        this.dataNo = dataNo;
        this.urlNo = urlNo;
        this.testNo = testNo;
        this.userNo = userNo;
        this.pageType = pageType;
        this.date = date;
        this.signed = signed;
        this.gender = gender;
        this.age = age;
        this.joinDate = joinDate;
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

    public boolean signed() {
        return signed;
    }

    public void setJoined(boolean signed) {
        this.signed = signed;
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

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "TestData [age=" + age + ", dataNo=" + dataNo + ", date=" + date + ", gender=" + gender + ", joinDate="
                + joinDate + ", pageType=" + pageType + ", signed=" + signed + ", testNo=" + testNo + ", urlNo=" + urlNo
                + ", userNo=" + userNo + "]";
    }

}
