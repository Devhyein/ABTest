package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminuser")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_no")
    private int adminNo; 

    @Column(name = "email")
    private String email; 

    @Column(name = "pw")
    private String pw;

    public AdminUser() {
    }

    public AdminUser(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }

    public AdminUser(int admin_no, String email, String pw) {
        this.adminNo = admin_no;
        this.email = email;
        this.pw = pw;
    }

    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int admin_no) {
        this.adminNo = admin_no;
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

    @Override
    public String toString() {
        return "AdminUser [adminNo=" + adminNo + ", email=" + email + ", pw=" + pw + "]";
    }

    
}
