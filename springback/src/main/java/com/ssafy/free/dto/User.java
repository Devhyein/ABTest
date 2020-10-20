package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_no")
    private int user_no; 

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "age")
    private int age;
    
    @Column(name = "history")
    private String history;

    public User() {
    }

    public User(int user_no, boolean gender, int age, String history) {
        this.user_no = user_no;
        this.gender = gender;
        this.age = age;
        this.history = history;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", gender=" + gender + ", history=" + history + ", user_no=" + user_no + "]";
    }

    
}
