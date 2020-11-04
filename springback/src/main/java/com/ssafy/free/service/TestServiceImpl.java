package com.ssafy.free.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import com.ssafy.free.dto.Analysis;
import com.ssafy.free.dto.Test;
import com.ssafy.free.dto.TestData;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestDataRepository testDataRepository;

    @Override
    public Analysis getDetailTest(int test_no) {
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                Analysis analysis = new Analysis(test);
                DecimalFormat form = new DecimalFormat("#.#");

                // 테스트 데이터 전체 가져오기 PAGE TYPE별로...
                List<TestData> dataA = testDataRepository.findAllByTestNoAndPageType(test_no, "A");
                List<TestData> dataB = testDataRepository.findAllByTestNoAndPageType(test_no, "B");

                // // 전환율 계산
                // float conversionA = 0;
                // for (TestData data : dataA) {
                // data.getTotalPageCnt()
                // }

                // 이탈률 계산

                // 가입률 계산

                // 구매율 계산

                return analysis;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
