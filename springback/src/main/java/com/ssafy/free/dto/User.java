package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_no")
    private int userNo;

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "email")
    private String email;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "date")
    private LocalDate date;

    public User() {
    }

    public User(int userNo, int testNo, String email, String pageType, LocalDate date) {
        this.userNo = userNo;
        this.testNo = testNo;
        this.email = email;
        this.pageType = pageType;
        this.date = date;
    }

    public User(int testNo, String email, String pageType, LocalDate date) {
        this.testNo = testNo;
        this.email = email;
        this.pageType = pageType;
        this.date = date;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User [email=" + email + ", pageType=" + pageType + ", testNo=" + testNo + ", userNo=" + userNo
                + ", date=" + date + "]";
    }

}
