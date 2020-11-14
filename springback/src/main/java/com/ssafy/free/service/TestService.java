package com.ssafy.free.service;

import com.ssafy.free.dto.Analysis.*;

public interface TestService {

    Analysis getDetailTest(int testno);

    AnalysisConversionWithUrl getDetailTestConversionWithUrl(int testno);

    Analysis getDetailTestGender(int test_no);

    TableDataGender getTableDataGender(int test_no);

    TableDataAge getTableDataAge(int test_no);

}
