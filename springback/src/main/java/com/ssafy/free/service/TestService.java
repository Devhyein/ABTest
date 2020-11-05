package com.ssafy.free.service;

import com.ssafy.free.dto.Analysis;
import com.ssafy.free.dto.AnalysisConversionWithUrl;

public interface TestService {

    Analysis getDetailTest(int testno);

    AnalysisConversionWithUrl getDetailTestConversionWithUrl(int testno);

}
