package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_no")
    private int testNo; 

    @Column(name = "admin_no")
    private int adminNo; 

    @Column(name = "test_title")
    private String testTitle; 

    @Column(name = "test_a")
    private String testA; 

    @Column(name = "test_b")
    private String testB; 

    @Column(name = "start")
    private LocalDate start; 

    @Column(name = "end")
    private LocalDate end; 

    @Column(name = "per_a")
    private int perA; 

    @Column(name = "per_b")
    private int perB; 

    @Column(name = "status")
    private String status;

    public Test() {
    }

    public Test(int test_no, int admin_no, String test_title, String test_a, String test_b, LocalDate start,
            LocalDate end, int per_a, int per_b, String status) {
        this.testNo = test_no;
        this.adminNo = admin_no;
        this.testTitle = test_title;
        this.testA = test_a;
        this.testB = test_b;
        this.start = start;
        this.end = end;
        this.perA = per_a;
        this.perB = per_b;
        this.status = status;
    }

    public Test(int admin_no, String test_title, String test_a, String test_b, LocalDate start, LocalDate end,
            int per_a, int per_b, String status) {
        this.adminNo = admin_no;
        this.testTitle = test_title;
        this.testA = test_a;
        this.testB = test_b;
        this.start = start;
        this.end = end;
        this.perA = per_a;
        this.perB = per_b;
        this.status = status;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int test_no) {
        this.testNo = test_no;
    }

    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int admin_no) {
        this.adminNo = admin_no;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String test_title) {
        this.testTitle = test_title;
    }

    public String getTestA() {
        return testA;
    }

    public void setTestA(String test_a) {
        this.testA = test_a;
    }

    public String getTestB() {
        return testB;
    }

    public void setTestB(String test_b) {
        this.testB = test_b;
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

    public int getPerA() {
        return perA;
    }

    public void setPerA(int per_a) {
        this.perA = per_a;
    }

    public int getPerB() {
        return perB;
    }

    public void setPerB(int per_b) {
        this.perB = per_b;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test [admin_no=" + adminNo + ", end=" + end + ", per_a=" + perA + ", per_b=" + perB + ", start="
                + start + ", status=" + status + ", test_a=" + testA + ", test_b=" + testB + ", test_no=" + testNo
                + ", test_title=" + testTitle + "]";
    }

   
}
