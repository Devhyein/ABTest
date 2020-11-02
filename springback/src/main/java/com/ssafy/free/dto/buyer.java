package com.ssafy.free.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buyer")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buyer_no")
    private int buyerNo; 

    @Column(name = "product_no")
    private int productNo; 

    @Column(name = "test_no")
    private int testNo; 

    @Column(name = "user_no")
    private int userNo;
    
    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "page_type")
    private String pageType;

    public Buyer() {
    }

    public Buyer(int buyerNo, int productNo, int testNo, int userNo, LocalDate date, String pageType) {
        this.buyerNo = buyerNo;
        this.productNo = productNo;
        this.testNo = testNo;
        this.userNo = userNo;
        this.date = date;
        this.pageType = pageType;
    }

    public int getBuyerNo() {
        return buyerNo;
    }

    public void setBuyerNo(int buyerNo) {
        this.buyerNo = buyerNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Override
    public String toString() {
        return "buyer [buyerNo=" + buyerNo + ", date=" + date + ", pageType=" + pageType + ", productNo=" + productNo
                + ", testNo=" + testNo + ", userNo=" + userNo + "]";
    }


}
