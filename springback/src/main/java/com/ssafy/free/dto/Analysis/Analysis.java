package com.ssafy.free.dto.Analysis;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.free.dto.Test;

public class Analysis {
    private int test_no;
    private String test_title;
    private LocalDate start;
    private LocalDate end;
    private String status;
    private List<Float> conversionA;
    private List<Float> conversionB;
    private float con_rate;
    private List<Float> bounceA;
    private List<Float> bounceB;
    private float bo_rate;
    private List<Float> joinA;
    private List<Float> joinB;
    private float jo_rate;
    private List<Float> purchaseA;
    private List<Float> purchaseB;
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

    public Analysis(int test_no, String test_title, LocalDate start, LocalDate end, String status,
            List<Float> conversionA, List<Float> conversionB, float con_rate, List<Float> bounceA, List<Float> bounceB,
            float bo_rate, List<Float> joinA, List<Float> joinB, float jo_rate, List<Float> purchaseA,
            List<Float> purchaseB, float pur_rate) {
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

    public List<Float> getConversionA() {
        return conversionA;
    }

    public void setConversionA(List<Float> conversionA) {
        this.conversionA = conversionA;
    }

    public List<Float> getConversionB() {
        return conversionB;
    }

    public void setConversionB(List<Float> conversionB) {
        this.conversionB = conversionB;
    }

    public float getCon_rate() {
        return con_rate;
    }

    public void setCon_rate(float con_rate) {
        this.con_rate = con_rate;
    }

    public List<Float> getBounceA() {
        return bounceA;
    }

    public void setBounceA(List<Float> bounceA) {
        this.bounceA = bounceA;
    }

    public List<Float> getBounceB() {
        return bounceB;
    }

    public void setBounceB(List<Float> bounceB) {
        this.bounceB = bounceB;
    }

    public float getBo_rate() {
        return bo_rate;
    }

    public void setBo_rate(float bo_rate) {
        this.bo_rate = bo_rate;
    }

    public List<Float> getJoinA() {
        return joinA;
    }

    public void setJoinA(List<Float> joinA) {
        this.joinA = joinA;
    }

    public List<Float> getJoinB() {
        return joinB;
    }

    public void setJoinB(List<Float> joinB) {
        this.joinB = joinB;
    }

    public float getJo_rate() {
        return jo_rate;
    }

    public void setJo_rate(float jo_rate) {
        this.jo_rate = jo_rate;
    }

    public List<Float> getPurchaseA() {
        return purchaseA;
    }

    public void setPurchaseA(List<Float> purchaseA) {
        this.purchaseA = purchaseA;
    }

    public List<Float> getPurchaseB() {
        return purchaseB;
    }

    public void setPurchaseB(List<Float> purchaseB) {
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
