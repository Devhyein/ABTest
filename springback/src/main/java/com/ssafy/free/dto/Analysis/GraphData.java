package com.ssafy.free.dto.Analysis;

import java.time.LocalDate;
import java.util.List;

public class GraphData {
    private List<LocalDate> Date;
    private List<Float> AChartData;
    private List<Float> BChartData;

    public GraphData() {
    }

    public GraphData(List<LocalDate> date, List<Float> aChartData, List<Float> bChartData) {
        Date = date;
        AChartData = aChartData;
        BChartData = bChartData;
    }

    public List<LocalDate> getDate() {
        return Date;
    }

    public void setDate(List<LocalDate> date) {
        Date = date;
    }

    public List<Float> getAChartData() {
        return AChartData;
    }

    public void setAChartData(List<Float> aChartData) {
        AChartData = aChartData;
    }

    public List<Float> getBChartData() {
        return BChartData;
    }

    public void setBChartData(List<Float> bChartData) {
        BChartData = bChartData;
    }

    @Override
    public String toString() {
        return "GraphData [AChartData=" + AChartData + ", BChartData=" + BChartData + ", Date=" + Date + "]";
    }

}
