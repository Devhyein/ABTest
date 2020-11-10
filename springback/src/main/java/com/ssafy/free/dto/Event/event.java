package com.ssafy.free.dto.Event;

import java.time.LocalDate;

import lombok.Data;

@Data
public class event {
    private int test_no;
    private String page_type;
    private String Session_id;
    private String url;
    private LocalDate date;
    private boolean signed;
}
