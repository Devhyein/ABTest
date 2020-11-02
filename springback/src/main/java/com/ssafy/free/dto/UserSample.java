package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersample")
public class UserSample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_no")
    private int userNo; 

    @Column(name = "test_no")
    private int testNo; 

    @Column(name = "admin_no")
    private int adminNo; 

    @Column(name = "email")
    private String email; 

    @Column(name = "pw")
    private String pw; 

    @Column(name = "age")
    private int age; 

    @Column(name = "gender")
    private String gender;

    @Column(name = "page_type")
    private String pageType; 

    @Column(name = "join_date")
    private LocalDate joinDate;

    public UserSample() {
    }

    public UserSample(int userNo, int testNo, int adminNo, String email, String pw, int age, String gender,
            String pageType, LocalDate joinDate) {
        this.userNo = userNo;
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
        this.pageType = pageType;
        this.joinDate = joinDate;
    }

    public UserSample(int testNo, int adminNo, String email, String pw, int age, String gender,
    String pageType, LocalDate joinDate) {
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
        this.pageType = pageType;
        this.joinDate = joinDate;
        }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int adminNo) {
        this.adminNo = adminNo;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "UserSample [adminNo=" + adminNo + ", age=" + age + ", email=" + email + ", gender=" + gender
                + ", pageType=" + pageType + ", pw=" + pw + ", testNo=" + testNo + ", userNo=" + userNo + ", joinDate="+joinDate+"]";
    }

}
