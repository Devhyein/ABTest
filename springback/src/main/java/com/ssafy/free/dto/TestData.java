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
public class TestData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "data_no")
    private int dataNo;

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "url_cnt")
    private int urlCnt;

    @Column(name = "url")
    private String url;

    public TestData() {
    }

    public TestData(int dataNo, int testNo, String pageType, LocalDate date, int urlCnt, String url) {
        this.dataNo = dataNo;
        this.testNo = testNo;
        this.pageType = pageType;
        this.date = date;
        this.urlCnt = urlCnt;
        this.url = url;
    }

    public TestData(int testNo, String pageType, LocalDate date, int urlCnt, String url) {
        this.testNo = testNo;
        this.pageType = pageType;
        this.date = date;
        this.urlCnt = urlCnt;
        this.url = url;
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

    public int getUrlCnt() {
        return urlCnt;
    }

    public void setUrlCnt(int urlCnt) {
        this.urlCnt = urlCnt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "testdata [dataNo=" + dataNo + ", date=" + date + ", urlCnt=" + urlCnt + ", pageType=" + pageType
                + ", testNo=" + testNo + ", url=" + url + "]";
    }

}
