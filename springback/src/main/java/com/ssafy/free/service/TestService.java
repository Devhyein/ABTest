package com.ssafy.free.service;

import com.ssafy.free.dto.Analysis;

public interface TestService {

    Analysis getDetailTest(int testno);

    Analysis getDetailTestConversionWithUrl(int testno);

}
