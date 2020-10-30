package com.ssafy.free.dto;

import java.time.LocalDate;

public class TestResponse {
    private int test_no; 
    private int admin_no; 
    private String test_title; 
    private String test_a; 
    private String test_b; 
    private LocalDate start; 
    private LocalDate end; 
    private int per_a; 
    private int per_b; 
    private String status;
    private String url_a;
    private String url_b;

    public TestResponse() {
    }

    public TestResponse(int test_no, int admin_no, String test_title, String test_a, String test_b, LocalDate start,
            LocalDate end, int per_a, int per_b, String status, String url_a, String url_b) {
        this.test_no = test_no;
        this.admin_no = admin_no;
        this.test_title = test_title;
        this.test_a = test_a;
        this.test_b = test_b;
        this.start = start;
        this.end = end;
        this.per_a = per_a;
        this.per_b = per_b;
        this.status = status;
        this.url_a = url_a;
        this.url_b = url_b;
    }

    public TestResponse(int admin_no, String test_title, String test_a, String test_b, LocalDate start, LocalDate end,
            int per_a, int per_b, String status, String url_a, String url_b) {
        this.admin_no = admin_no;
        this.test_title = test_title;
        this.test_a = test_a;
        this.test_b = test_b;
        this.start = start;
        this.end = end;
        this.per_a = per_a;
        this.per_b = per_b;
        this.status = status;
        this.url_a = url_a;
        this.url_b = url_b;
    }

    public int getTest_no() {
        return test_no;
    }

    public void setTest_no(int test_no) {
        this.test_no = test_no;
    }

    public int getAdmin_no() {
        return admin_no;
    }

    public void setAdmin_no(int admin_no) {
        this.admin_no = admin_no;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public String getTest_a() {
        return test_a;
    }

    public void setTest_a(String test_a) {
        this.test_a = test_a;
    }

    public String getTest_b() {
        return test_b;
    }

    public void setTest_b(String test_b) {
        this.test_b = test_b;
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

    public int getPer_a() {
        return per_a;
    }

    public void setPer_a(int per_a) {
        this.per_a = per_a;
    }

    public int getPer_b() {
        return per_b;
    }

    public void setPer_b(int per_b) {
        this.per_b = per_b;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl_a() {
        return url_a;
    }

    public void setUrl_a(String url_a) {
        this.url_a = url_a;
    }

    public String getUrl_b() {
        return url_b;
    }

    public void setUrl_b(String url_b) {
        this.url_b = url_b;
    }

    @Override
    public String toString() {
        return "TestResponse [admin_no=" + admin_no + ", end=" + end + ", per_a=" + per_a + ", per_b=" + per_b
                + ", start=" + start + ", status=" + status + ", test_a=" + test_a + ", test_b=" + test_b + ", test_no="
                + test_no + ", test_title=" + test_title + ", url_a=" + url_a + ", url_b=" + url_b + "]";
    }

   
    
}
