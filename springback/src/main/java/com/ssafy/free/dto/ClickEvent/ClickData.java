package com.ssafy.free.dto.ClickEvent;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClickData {
    private int url_no;
    private int test_no;
    private int user_no;
    private String page_type;
    private LocalDate date;
    private boolean signed;
    private String gender;
    private int age;
    private LocalDate join_date;

    public ClickData() {
    }

    public ClickData(int url_no, int test_no, int user_no, String page_type, LocalDate date, boolean signed,
            String gender, int age, LocalDate join_date) {
        this.url_no = url_no;
        this.test_no = test_no;
        this.user_no = user_no;
        this.page_type = page_type;
        this.date = date;
        this.signed = signed;
        this.gender = gender;
        this.age = age;
        this.join_date = join_date;
    }

}
