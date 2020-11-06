package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagecnt")
public class PageCnt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "page_no")
    private int pageNo;

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "cntA")
    private int cntA;

    @Column(name = "cntB")
    private int cntB;

    @Column(name = "date")
    private LocalDate date;

    public PageCnt() {
    }

    public PageCnt(int pageNo, int testNo, int cntA, int cntB, LocalDate date) {
        this.pageNo = pageNo;
        this.testNo = testNo;
        this.cntA = cntA;
        this.cntB = cntB;
        this.date = date;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public int getCntA() {
        return cntA;
    }

    public void setCntA(int cntA) {
        this.cntA = cntA;
    }

    public int getCntB() {
        return cntB;
    }

    public void setCntB(int cntB) {
        this.cntB = cntB;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PageCnt [cntA=" + cntA + ", cntB=" + cntB + ", date=" + date + ", pageNo=" + pageNo + ", testNo="
                + testNo + "]";
    }

}
