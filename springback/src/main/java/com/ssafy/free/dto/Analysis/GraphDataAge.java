package com.ssafy.free.dto.Analysis;

import java.util.List;

public class GraphDataAge {
    private List<Float> AChartData;
    private List<Float> BChartData;

    public GraphDataAge() {
    }

    public GraphDataAge(List<Float> aChartData, List<Float> bChartData) {
        AChartData = aChartData;
        BChartData = bChartData;
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
        return "GraphDataAge [AChartData=" + AChartData + ", BChartData=" + BChartData + "]";
    }

}
