package com.ssafy.free.dto.Analysis;

import java.util.List;

public class AnalysisConversionWithUrl {
    private List<String> urls;
    private List<Float> conversionA;
    private List<Float> conversionB;

    public AnalysisConversionWithUrl() {
    }

    public AnalysisConversionWithUrl(List<String> urls, List<Float> conversionA, List<Float> conversionB) {
        this.urls = urls;
        this.conversionA = conversionA;
        this.conversionB = conversionB;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<Float> getConversionA() {
        return conversionA;
    }

    public void setConversionA(List<Float> conversionA) {
        this.conversionA = conversionA;
    }

    public List<Float> getConversionB() {
        return conversionB;
    }

    public void setConversionB(List<Float> conversionB) {
        this.conversionB = conversionB;
    }

    @Override
    public String toString() {
        return "AnalysisConversionWithUrl [conversionA=" + conversionA + ", conversionB=" + conversionB + ", urls="
                + urls + "]";
    }

}
