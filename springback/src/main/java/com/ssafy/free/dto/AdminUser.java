package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AdminUser")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_no")
    private int admin_no; 

    @Column(name = "id")
    private String id; 

    @Column(name = "pw")
    private String pw;

    public AdminUser() {
    }

    public AdminUser(int admin_no, String id, String pw) {
        this.admin_no = admin_no;
        this.id = id;
        this.pw = pw;
    }

    public int getAdmin_no() {
        return admin_no;
    }

    public void setAdmin_no(int admin_no) {
        this.admin_no = admin_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "AdminUser [admin_no=" + admin_no + ", id=" + id + ", pw=" + pw + "]";
    }

    
}
