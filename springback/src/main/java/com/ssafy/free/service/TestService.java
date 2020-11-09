package com.ssafy.free.service;

import com.ssafy.free.dto.Analysis.*;

public interface TestService {

    Analysis getDetailTest(int testno);

    AnalysisConversionWithUrl getDetailTestConversionWithUrl(int testno);

    Analysis getDetailRate(Analysis detail);

}
