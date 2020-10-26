package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_no")
    private int test_no; 

    @Column(name = "admin_no")
    private int adminNo; 

    @Column(name = "test_title")
    private String test_title; 

    @Column(name = "test_a")
    private String test_a; 

    @Column(name = "test_b")
    private String test_b; 

    @Column(name = "start")
    private LocalDate start; 

    @Column(name = "end")
    private LocalDate end; 

    @Column(name = "per_a")
    private int per_a; 

    @Column(name = "per_b")
    private int per_b; 

    @Column(name = "status")
    private String status;

    public Test() {
    }

    public Test(int test_no, int admin_no, String test_title, String test_a, String test_b, LocalDate start,
            LocalDate end, int per_a, int per_b, String status) {
        this.test_no = test_no;
        this.adminNo = admin_no;
        this.test_title = test_title;
        this.test_a = test_a;
        this.test_b = test_b;
        this.start = start;
        this.end = end;
        this.per_a = per_a;
        this.per_b = per_b;
        this.status = status;
    }

    public Test(int admin_no, String test_title, String test_a, String test_b, LocalDate start, LocalDate end,
            int per_a, int per_b, String status) {
        this.adminNo = admin_no;
        this.test_title = test_title;
        this.test_a = test_a;
        this.test_b = test_b;
        this.start = start;
        this.end = end;
        this.per_a = per_a;
        this.per_b = per_b;
        this.status = status;
    }

    public int getTest_no() {
        return test_no;
    }

    public void setTest_no(int test_no) {
        this.test_no = test_no;
    }

    public int getAdmin_no() {
        return adminNo;
    }

    public void setAdmin_no(int admin_no) {
        this.adminNo = admin_no;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public String getTest_a() {
        return test_a;
    }

    public void setTest_a(String test_a) {
        this.test_a = test_a;
    }

    public String getTest_b() {
        return test_b;
    }

    public void setTest_b(String test_b) {
        this.test_b = test_b;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getPer_a() {
        return per_a;
    }

    public void setPer_a(int per_a) {
        this.per_a = per_a;
    }

    public int getPer_b() {
        return per_b;
    }

    public void setPer_b(int per_b) {
        this.per_b = per_b;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test [admin_no=" + adminNo + ", end=" + end + ", per_a=" + per_a + ", per_b=" + per_b + ", start="
                + start + ", status=" + status + ", test_a=" + test_a + ", test_b=" + test_b + ", test_no=" + test_no
                + ", test_title=" + test_title + "]";
    }

   
}
