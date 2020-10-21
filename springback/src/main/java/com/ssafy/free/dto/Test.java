package com.ssafy.free.dto;

import java.time.LocalDateTime;

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

    @Column(name = "test_title")
    private String test_title; 

    @Column(name = "test_a")
    private String test_a; 

    @Column(name = "test_b")
    private String test_b; 

    @Column(name = "start")
    private LocalDateTime start; 

    @Column(name = "end")
    private LocalDateTime end; 

    @Column(name = "per_a")
    private int per_a; 

    @Column(name = "per_b")
    private int per_b; 

    @Column(name = "status")
    private int status;

    public Test() {
    }

    public Test(int test_no, String test_title, String test_a, String test_b, LocalDateTime start, LocalDateTime end,
            int per_a, int per_b, int status) {
        this.test_no = test_no;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test [end=" + end + ", per_a=" + per_a + ", per_b=" + per_b + ", start=" + start + ", status=" + status
                + ", test_a=" + test_a + ", test_b=" + test_b + ", test_no=" + test_no + ", test_title=" + test_title
                + "]";
    }

   
    
}