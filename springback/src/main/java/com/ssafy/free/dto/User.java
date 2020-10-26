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

    @Column(name = "email")
    private String email;
    
    @Column(name = "history")
    private String history;

    public User() {
    }

    public User(String email, String history) {
        this.email = email;
        this.history = history;
    }

    public User(int user_no, String email, String history) {
        this.user_no = user_no;
        this.email = email;
        this.history = history;
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", history=" + history + ", user_no=" + user_no + "]";
    }

    

}
