package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserSample")
public class UserSample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_no")
    private int user_no; 

    @Column(name = "email")
    private String email; 

    @Column(name = "pw")
    private String pw; 

    @Column(name = "age")
    private int age; 

    @Column(name = "gender")
    private int gender;

    public UserSample() {
    }

    public UserSample(int user_no, String email, String pw, int age, int gender) {
        this.user_no = user_no;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserSample [age=" + age + ", email=" + email + ", gender=" + gender + ", pw=" + pw + ", user_no="
                + user_no + "]";
    }

}
