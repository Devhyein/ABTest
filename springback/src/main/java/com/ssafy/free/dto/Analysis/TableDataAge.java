package com.ssafy.free.dto.Analysis;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.free.dto.Admin.*;

import lombok.Data;

@Data
public class TableDataAge {
    private int test_no;
    private String test_title;
    private LocalDate start;
    private LocalDate end;
    private String status;
    private List<Float> conversionA;
    private List<Float> conversionB;
    private List<Float> con_rate;
    private List<Float> bounceA;
    private List<Float> bounceB;
    private List<Float> bo_rate;

    public TableDataAge() {
    }

    public TableDataAge(Test test) {
        this.test_no = test.getTestNo();
        this.test_title = test.getTestTitle();
        this.start = test.getStart();
        this.end = test.getEnd();
        this.status = test.getStatus();
    }

}
