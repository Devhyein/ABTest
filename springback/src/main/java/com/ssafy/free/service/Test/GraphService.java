package com.ssafy.free.service.Test;

import com.ssafy.free.dto.Analysis.GraphData;
import com.ssafy.free.dto.Analysis.GraphDataAge;
import com.ssafy.free.dto.Analysis.GraphDataGender;

public interface GraphService {

    public GraphData getChartConversion(int test_no);

    public GraphData getChartBounce(int test_no);

    public GraphData getChartJoin(int test_no);

    public GraphData getChartPurchase(int test_no);

    public GraphDataGender getChartGenderConversion(int test_no);

    public GraphDataGender getChartGenderBounce(int test_no);

    public GraphDataAge getChartAgeConversion(int test_no);

    public GraphDataAge getChartAgeBounce(int test_no);

}
