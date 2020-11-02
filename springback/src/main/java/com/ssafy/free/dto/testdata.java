package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testdata")
public class testdata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "data_no")
    private int dataNo; 

    @Column(name = "test_no")
    private int testNo;
    
    @Column(name = "admin_no")
    private int adminNo; 

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "date")
    private LocalDate date; 
    
    @Column(name = "total_page_cnt")
    private int totalPageCnt; 

    @Column(name = "next_page_cnt")
    private int nextPageCnt;

    public testdata() {
    }

    public testdata(int dataNo, int testNo, int adminNo, String pageType, LocalDate date, int totalPageCnt,
            int nextPageCnt) {
        this.dataNo = dataNo;
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.pageType = pageType;
        this.date = date;
        this.totalPageCnt = totalPageCnt;
        this.nextPageCnt = nextPageCnt;
    }

    public testdata(int testNo, int adminNo, String pageType, LocalDate date, int totalPageCnt, int nextPageCnt) {
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.pageType = pageType;
        this.date = date;
        this.totalPageCnt = totalPageCnt;
        this.nextPageCnt = nextPageCnt;
    }

    public int getDataNo() {
        return dataNo;
    }

    public void setDataNo(int dataNo) {
        this.dataNo = dataNo;
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

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public int getNextPageCnt() {
        return nextPageCnt;
    }

    public void setNextPageCnt(int nextPageCnt) {
        this.nextPageCnt = nextPageCnt;
    }

    @Override
    public String toString() {
        return "testdata [adminNo=" + adminNo + ", dataNo=" + dataNo + ", date=" + date + ", nextPageCnt=" + nextPageCnt
                + ", pageType=" + pageType + ", testNo=" + testNo + ", totalPageCnt=" + totalPageCnt + "]";
    }

}