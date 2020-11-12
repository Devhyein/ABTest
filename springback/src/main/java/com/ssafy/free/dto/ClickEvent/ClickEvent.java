package com.ssafy.free.dto.ClickEvent;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClickEvent {
    private int test_no;
    private String page_type;
    private String Session_id;
    private String url;
    private LocalDate date;
    private boolean signed;

    public ClickEvent() {
    }

    public ClickEvent(int test_no, String page_type, String session_id, String url, LocalDate date, boolean signed) {
        this.test_no = test_no;
        this.page_type = page_type;
        Session_id = session_id;
        this.url = url;
        this.date = date;
        this.signed = signed;
    }

}
