package com.ssafy.free.dto.sample;

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

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "email")
    private String email;

    @Column(name = "pw")
    private String pw;

    @Column(name = "birth")
    private LocalDate birth;

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

    public UserSample(int userNo, int testNo, String sessionId, String email, String pw, LocalDate birth, int age,
            String gender, String pageType, LocalDate joinDate) {
        this.userNo = userNo;
        this.testNo = testNo;
        this.sessionId = sessionId;
        this.email = email;
        this.pw = pw;
        this.birth = birth;
        this.age = age;
        this.gender = gender;
        this.pageType = pageType;
        this.joinDate = joinDate;
    }

    public UserSample(int testNo, String sessionId, String email, String pw, LocalDate birth, int age, String gender,
            String pageType, LocalDate joinDate) {
        this.testNo = testNo;
        this.sessionId = sessionId;
        this.email = email;
        this.pw = pw;
        this.birth = birth;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "UserSample [age=" + age + ", birth=" + birth + ", email=" + email + ", gender=" + gender + ", joinDate="
                + joinDate + ", pageType=" + pageType + ", pw=" + pw + ", sessionId=" + sessionId + ", testNo=" + testNo
                + ", userNo=" + userNo + "]";
    }

}
