package com.ssafy.free.dto.Analysis;

import java.time.LocalDate;

import com.ssafy.free.dto.Admin.*;

import lombok.Data;

@Data
public class TableDataGender {
    private int test_no;
    private String test_title;
    private LocalDate start;
    private LocalDate end;
    private String status;
    private float conversionAMale;
    private float conversionAFemale;
    private float conversionBMale;
    private float conversionBFemale;
    private float con_rateMale;
    private float con_rateFemale;
    private float bounceAMale;
    private float bounceAFemale;
    private float bounceBMale;
    private float bounceBFemale;
    private float bo_rateMale;
    private float bo_rateFemale;

    public TableDataGender() {
    }

    public TableDataGender(Test test) {
        this.test_no = test.getTestNo();
        this.test_title = test.getTestTitle();
        this.start = test.getStart();
        this.end = test.getEnd();
        this.status = test.getStatus();
    }

}
