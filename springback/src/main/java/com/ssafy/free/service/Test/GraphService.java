package com.ssafy.free.service.Test;

import com.ssafy.free.dto.Analysis.GraphData;

public interface GraphService {

    public GraphData getChartConversion(int test_no);

    public GraphData getChartBounce(int test_no);

    public GraphData getChartJoin(int test_no);

}
