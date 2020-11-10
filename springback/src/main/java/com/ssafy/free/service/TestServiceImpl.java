package com.ssafy.free.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Analysis.*;
import com.ssafy.free.dto.Admin.*;
import com.ssafy.free.repository.BuyerRepository;
import com.ssafy.free.repository.PageCntRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;
import com.ssafy.free.repository.UrlAttributeRepository;
import com.ssafy.free.repository.ClientConsumerRepository;
import com.ssafy.free.repository.UserSampleRepository;

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

    @Autowired
    ClientConsumerRepository userRepo;

    @Autowired
    UserSampleRepository userSampleRepo;

    @Autowired
    BuyerRepository buyerRepo;

    // 표에 들어갈 데이터
    @Override
    public Analysis getDetailTest(int test_no) {
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                Analysis analysis = new Analysis(test);

                // 전환율
                // test_no를 가진 testdata를 모두 가져와서
                float conA = testDataRepository.countByTestNoAndPageType(test.getTestNo(), "A");
                float conB = testDataRepository.countByTestNoAndPageType(test.getTestNo(), "B");

                List<PageCnt> totalPage = pageRepo.findAllByTestNo(test.getTestNo());
                float totalA = 0;
                float totalB = 0;

                for (PageCnt page : totalPage) {
                    totalA += page.getCntA();
                    totalB += page.getCntB();
                }

                float tempA = 0;
                float tempB = 0;

                if (totalA != 0)
                    tempA = (conA / totalA);
                if (totalB != 0)
                    tempB = (conB / totalB);

                analysis.setConversionA((float) (Math.round(tempA * 1000) / 10.0));
                analysis.setConversionB((float) (Math.round(tempB * 1000) / 10.0));

                analysis.setCon_rate((float) (Math.round((tempB - tempA) * 1000) / 10.0));

                // 이탈률
                analysis.setBounceA((float) (Math.round((1 - (conA / totalA)) * 1000) / 10.0));
                analysis.setBounceB((float) (Math.round((1 - (conB / totalB)) * 1000) / 10.0));
                analysis.setBo_rate(
                        (float) (Math.round(((1 - (conB / totalB)) - (1 - (conA / totalA))) * 1000) / 10.0));

                // 가입률
                totalA = userRepo.countByTestNoAndPageType(test.getTestNo(), "A");
                totalB = userRepo.countByTestNoAndPageType(test.getTestNo(), "B");

                float joinA = userSampleRepo.countByTestNoAndPageType(test.getTestNo(), "A");
                float joinB = userSampleRepo.countByTestNoAndPageType(test.getTestNo(), "B");

                tempA = 0;
                tempB = 0;

                if (totalA != 0)
                    tempA = (joinA / totalA);
                if (totalB != 0)
                    tempB = (joinB / totalB);
                analysis.setJoinA((float) (Math.round(tempA * 1000) / 10.0));
                analysis.setJoinB((float) (Math.round(tempB * 1000) / 10.0));
                analysis.setJo_rate((float) (Math.round((tempB - tempA) * 1000) / 10.0));

                // 구매율
                // 구매율의 기준은 상품 조회 페이지에 넘어간 유저들 기준인가? 아니면 이 사이트에 접속한 모든 시용자 기준인가?
                // 아니면 회원가입한 유저 기준인가...

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

    @Override
    public Analysis getDetailTestGender(int test_no) {

        return null;
    }

}
