package com.ssafy.free.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Analysis;
import com.ssafy.free.dto.AnalysisConversionWithUrl;
import com.ssafy.free.dto.PageCnt;
import com.ssafy.free.dto.Test;
import com.ssafy.free.dto.UrlAttribute;
import com.ssafy.free.repository.PageCntRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;
import com.ssafy.free.repository.UrlAttributeRepository;

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

    @Autowired
    UrlAttributeRepository urlRepo;

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

                // 이탈률 계산 (1 - 전환률)
                List<Float> bounceA = new ArrayList<Float>();
                List<Float> bounceB = new ArrayList<Float>();

                for (float a : conversionA) {
                    bounceA.add(1 - a);
                }
                for (float b : conversionB) {
                    bounceB.add(1 - b);
                }
                analysis.setBounceA(bounceA);
                analysis.setBounceB(bounceB);

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

    @Override
    public AnalysisConversionWithUrl getDetailTestConversionWithUrl(int testno) {
        AnalysisConversionWithUrl result = new AnalysisConversionWithUrl();

        List<String> urls = new ArrayList<>();
        List<Float> conversionA = new ArrayList<>();
        List<Float> conversionB = new ArrayList<>();

        List<UrlAttribute> urlList = urlRepo.findByTestNo(testno);
        List<PageCnt> pageList = pageRepo.findAllByTestNo(testno);
        float cntA = 0;
        float cntB = 0;
        for (PageCnt page : pageList) {
            cntA += page.getCntA();
            cntB += page.getCntB();
        }

        for (UrlAttribute url : urlList) {
            urls.add(url.getUrlName());
            // url이름으로 url_no찾기
            int urlNo = urlRepo.findByTestNoAndUrlName(testno, url.getUrlName()).getUrlNo();
            // 해당 url의 클릭 횟수 count구하기
            float cntUrlA = testDataRepository.countByTestNoAndPageTypeAndUrlNo(testno, "A", urlNo);
            float cntUrlB = testDataRepository.countByTestNoAndPageTypeAndUrlNo(testno, "B", urlNo);

            float A = 0;
            float B = 0;
            if (cntA > 0)
                A = cntUrlA / cntA;
            if (cntB > 0)
                B = cntUrlB / cntB;
            conversionA.add((float) (Math.round(A * 100) / 100.0));
            conversionB.add((float) (Math.round(B * 100) / 100.0));

        }
        result.setUrls(urls);
        result.setConversionA(conversionA);
        result.setConversionB(conversionB);

        return result;
    }

}
