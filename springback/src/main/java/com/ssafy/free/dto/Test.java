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

    @Column(name = "url_a")
    private String urlA; 

    @Column(name = "url_b")
    private String urlB; 

    public Test() {
    }

    public Test(int testNo, int adminNo, String testTitle, String testA, String testB, LocalDate start, LocalDate end,
            int perA, int perB, String status, String urlA, String urlB) {
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.testTitle = testTitle;
        this.testA = testA;
        this.testB = testB;
        this.start = start;
        this.end = end;
        this.perA = perA;
        this.perB = perB;
        this.status = status;
        this.urlA = urlA;
        this.urlB = urlB;
    }

    public Test(int adminNo, String testTitle, String testA, String testB, LocalDate start, LocalDate end, int perA,
            int perB, String status, String urlA, String urlB) {
        this.adminNo = adminNo;
        this.testTitle = testTitle;
        this.testA = testA;
        this.testB = testB;
        this.start = start;
        this.end = end;
        this.perA = perA;
        this.perB = perB;
        this.status = status;
        this.urlA = urlA;
        this.urlB = urlB;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int adminNo) {
        this.adminNo = adminNo;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestA() {
        return testA;
    }

    public void setTestA(String testA) {
        this.testA = testA;
    }

    public String getTestB() {
        return testB;
    }

    public void setTestB(String testB) {
        this.testB = testB;
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

    public void setPerA(int perA) {
        this.perA = perA;
    }

    public int getPerB() {
        return perB;
    }

    public void setPerB(int perB) {
        this.perB = perB;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlA() {
        return urlA;
    }

    public void setUrlA(String urlA) {
        this.urlA = urlA;
    }

    public String getUrlB() {
        return urlB;
    }

    public void setUrlB(String urlB) {
        this.urlB = urlB;
    }

    @Override
    public String toString() {
        return "Test [adminNo=" + adminNo + ", end=" + end + ", perA=" + perA + ", perB=" + perB + ", start=" + start
                + ", status=" + status + ", testA=" + testA + ", testB=" + testB + ", testNo=" + testNo + ", testTitle="
                + testTitle + ", urlA=" + urlA + ", urlB=" + urlB + "]";
    }

 
   
}
