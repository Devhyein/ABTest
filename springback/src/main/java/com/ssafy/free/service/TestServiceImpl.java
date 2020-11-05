package com.ssafy.free.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssafy.free.dto.Analysis;
import com.ssafy.free.dto.PageCnt;
import com.ssafy.free.dto.Test;
import com.ssafy.free.dto.TestData;
import com.ssafy.free.repository.PageCntRepository;
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

    @Autowired
    PageCntRepository pageRepo;

    @Override
    public Analysis getDetailTest(int test_no) {
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                Analysis analysis = new Analysis(test);
                LocalDate start = test.getStart();
                // 전체 전환율 계산
                List<Float> conversionA = new ArrayList<Float>();
                List<Float> conversionB = new ArrayList<Float>();
                // 전체 페이지 제공 회수
                List<PageCnt> pagecnt = pageRepo.findByTestNoOrderByDateAsc(test_no);
                for (PageCnt page : pagecnt) {
                    // 각 날짜의 전체 페이지 제공 횟수
                    LocalDate date = page.getDate();
                    while (!start.equals(date)) {
                        conversionA.add((float) 0);
                        conversionB.add((float) 0);
                        start = start.plusDays(1);
                    }
                    float cntA = page.getCntA();
                    float cntB = page.getCntB();

                    // 각 날짜의 전체 페이지 전환 횟수
                    float clickA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", date);
                    float clickB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", date);
                    float A = clickA / cntA;
                    conversionA.add((float) (Math.round(A * 100) / 100.0));
                    float B = clickB / cntB;
                    conversionB.add((float) (Math.round(B * 100) / 100.0));
                    start = start.plusDays(1);
                }
                start = start.minusDays(1);
                while (!start.equals(test.getEnd())) {
                    conversionA.add((float) 0);
                    conversionB.add((float) 0);
                    start = start.plusDays(1);
                }

                analysis.setConversionA(conversionA);
                analysis.setConversionB(conversionB);
                // 날짜별 페이지 제공 회수

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
