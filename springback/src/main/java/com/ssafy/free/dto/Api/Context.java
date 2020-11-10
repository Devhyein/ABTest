package com.ssafy.free.dto.Api;

import lombok.Data;

@Data
public class Context {

    private String session_id;
    private String page_type;
    private int test_no;
    private int bucketNumber;
    private boolean Progress;

}