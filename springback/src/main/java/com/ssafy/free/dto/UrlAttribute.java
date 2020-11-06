package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "urlattribute")
public class UrlAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "url_no")
    private int urlNo;

    @Column(name = "test_no")
    private int testNo;

    @Column(name = "url_name")
    private String urlName;

    public UrlAttribute() {
    }

    public UrlAttribute(int testNo, String urlName) {
        this.testNo = testNo;
        this.urlName = urlName;
    }

    public UrlAttribute(int urlNo, int testNo, String urlName) {
        this.urlNo = urlNo;
        this.testNo = testNo;
        this.urlName = urlName;
    }

    public int getUrlNo() {
        return urlNo;
    }

    public void setUrlNo(int urlNo) {
        this.urlNo = urlNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return "UrlAttribute [testNo=" + testNo + ", urlName=" + urlName + ", urlNo=" + urlNo + "]";
    }

}
