package com.ssafy.free.dto.Analysis;

import java.time.LocalDate;

import com.ssafy.free.dto.Admin.*;

public class Analysis {
    private int test_no;
    private String test_title;
    private LocalDate start;
    private LocalDate end;
    private String status;
    private float conversionA;
    private float conversionB;
    private float con_rate;
    private float bounceA;
    private float bounceB;
    private float bo_rate;
    private float joinA;
    private float joinB;
    private float jo_rate;
    private float purchaseA;
    private float purchaseB;
    private float pur_rate;

    public Analysis() {
    }

    public Analysis(Test test) {
        this.test_no = test.getTestNo();
        this.test_title = test.getTestTitle();
        this.start = test.getStart();
        this.end = test.getEnd();
        this.status = test.getStatus();
    }

    public Analysis(int test_no, String test_title, LocalDate start, LocalDate end, String status, float conversionA,
            float conversionB, float con_rate, float bounceA, float bounceB, float bo_rate, float joinA, float joinB,
            float jo_rate, float purchaseA, float purchaseB, float pur_rate) {
        this.test_no = test_no;
        this.test_title = test_title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.conversionA = conversionA;
        this.conversionB = conversionB;
        this.con_rate = con_rate;
        this.bounceA = bounceA;
        this.bounceB = bounceB;
        this.bo_rate = bo_rate;
        this.joinA = joinA;
        this.joinB = joinB;
        this.jo_rate = jo_rate;
        this.purchaseA = purchaseA;
        this.purchaseB = purchaseB;
        this.pur_rate = pur_rate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getConversionA() {
        return conversionA;
    }

    public void setConversionA(float conversionA) {
        this.conversionA = conversionA;
    }

    public float getConversionB() {
        return conversionB;
    }

    public void setConversionB(float conversionB) {
        this.conversionB = conversionB;
    }

    public float getCon_rate() {
        return con_rate;
    }

    public void setCon_rate(float con_rate) {
        this.con_rate = con_rate;
    }

    public float getBounceA() {
        return bounceA;
    }

    public void setBounceA(float bounceA) {
        this.bounceA = bounceA;
    }

    public float getBounceB() {
        return bounceB;
    }

    public void setBounceB(float bounceB) {
        this.bounceB = bounceB;
    }

    public float getBo_rate() {
        return bo_rate;
    }

    public void setBo_rate(float bo_rate) {
        this.bo_rate = bo_rate;
    }

    public float getJoinA() {
        return joinA;
    }

    public void setJoinA(float joinA) {
        this.joinA = joinA;
    }

    public float getJoinB() {
        return joinB;
    }

    public void setJoinB(float joinB) {
        this.joinB = joinB;
    }

    public float getJo_rate() {
        return jo_rate;
    }

    public void setJo_rate(float jo_rate) {
        this.jo_rate = jo_rate;
    }

    public float getPurchaseA() {
        return purchaseA;
    }

    public void setPurchaseA(float purchaseA) {
        this.purchaseA = purchaseA;
    }

    public float getPurchaseB() {
        return purchaseB;
    }

    public void setPurchaseB(float purchaseB) {
        this.purchaseB = purchaseB;
    }

    public float getPur_rate() {
        return pur_rate;
    }

    public void setPur_rate(float pur_rate) {
        this.pur_rate = pur_rate;
    }

    @Override
    public String toString() {
        return "Analysis [bo_rate=" + bo_rate + ", bounceA=" + bounceA + ", bounceB=" + bounceB + ", con_rate="
                + con_rate + ", conversionA=" + conversionA + ", conversionB=" + conversionB + ", end=" + end
                + ", jo_rate=" + jo_rate + ", joinA=" + joinA + ", joinB=" + joinB + ", pur_rate=" + pur_rate
                + ", purchaseA=" + purchaseA + ", purchaseB=" + purchaseB + ", start=" + start + ", status=" + status
                + ", test_no=" + test_no + ", test_title=" + test_title + "]";
    }

}
