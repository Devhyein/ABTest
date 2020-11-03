package com.ssafy.free.dto;

import java.time.LocalDate;

public class Analysis {
    private String test_title;
    private LocalDate start;
    private LocalDate end;
    private String status;
    private float conversionA;
    private float conversionB;
    private float bounceA;
    private float bounceB;
    private float joinA;
    private float joinB;
    private float purchaseA;
    private float purchaseB;

    public Analysis() {
    }

    public Analysis(Test test) {
        this.test_title = test.getTestTitle();
        this.start = test.getStart();
        this.end = test.getEnd();
        this.status = test.getStatus();
    }

    public Analysis(String test_title, LocalDate start, LocalDate end, String status, float conversionA,
            float conversionB, float bounceA, float bounceB, float joinA, float joinB, float purchaseA,
            float purchaseB) {
        this.test_title = test_title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.conversionA = conversionA;
        this.conversionB = conversionB;
        this.bounceA = bounceA;
        this.bounceB = bounceB;
        this.joinA = joinA;
        this.joinB = joinB;
        this.purchaseA = purchaseA;
        this.purchaseB = purchaseB;
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

    @Override
    public String toString() {
        return "Analysis [bounceA=" + bounceA + ", bounceB=" + bounceB + ", conversionA=" + conversionA
                + ", conversionB=" + conversionB + ", end=" + end + ", joinA=" + joinA + ", joinB=" + joinB
                + ", purchaseA=" + purchaseA + ", purchaseB=" + purchaseB + ", start=" + start + ", status=" + status
                + ", test_title=" + test_title + "]";
    }

}
