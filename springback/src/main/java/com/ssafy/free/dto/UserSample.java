package com.ssafy.free.dto;

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

    @Column(name = "email")
    private String email; 

    @Column(name = "pw")
    private String pw; 

    @Column(name = "age")
    private int age; 

    @Column(name = "gender")
    private String gender;

    public UserSample() {
    }

    public UserSample(String email, String pw, int age, String gender) {
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
    }

    public UserSample(int userNo, String email, String pw, int age, String gender) {
        this.userNo = userNo;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "UserSample [age=" + age + ", emial=" + email + ", gender=" + gender + ", pw=" + pw + ", userNo="
                + userNo + "]";
    }

}
