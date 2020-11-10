package com.ssafy.free.dto.Analysis;

import java.util.List;

public class GraphDataGender {
    // private List<String> Date;
    private Float AMaleChartData;
    private Float AFemaleChartData;
    private Float BMaleChartData;
    private Float BFemaleChartData;

    public GraphDataGender() {
    }

    public GraphDataGender(Float aMaleChartData, Float aFemaleChartData, Float bMaleChartData, Float bFemaleChartData) {
        AMaleChartData = aMaleChartData;
        AFemaleChartData = aFemaleChartData;
        BMaleChartData = bMaleChartData;
        BFemaleChartData = bFemaleChartData;
    }

    public Float getAMaleChartData() {
        return AMaleChartData;
    }

    public void setAMaleChartData(Float aMaleChartData) {
        AMaleChartData = aMaleChartData;
    }

    public Float getAFemaleChartData() {
        return AFemaleChartData;
    }

    public void setAFemaleChartData(Float aFemaleChartData) {
        AFemaleChartData = aFemaleChartData;
    }

    public Float getBMaleChartData() {
        return BMaleChartData;
    }

    public void setBMaleChartData(Float bMaleChartData) {
        BMaleChartData = bMaleChartData;
    }

    public Float getBFemaleChartData() {
        return BFemaleChartData;
    }

    public void setBFemaleChartData(Float bFemaleChartData) {
        BFemaleChartData = bFemaleChartData;
    }

    @Override
    public String toString() {
        return "GraphDataGender [AFemaleChartData=" + AFemaleChartData + ", AMaleChartData=" + AMaleChartData
                + ", BFemaleChartData=" + BFemaleChartData + ", BMaleChartData=" + BMaleChartData + "]";
    }

}
