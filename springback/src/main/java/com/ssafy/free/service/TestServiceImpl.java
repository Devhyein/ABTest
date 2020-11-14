package com.ssafy.free.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Admin.UrlAttribute;
import com.ssafy.free.dto.Analysis.Analysis;
import com.ssafy.free.dto.Analysis.AnalysisConversionWithUrl;
import com.ssafy.free.repository.BuyerRepository;
import com.ssafy.free.repository.ClientConsumerRepository;
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
    UrlAttributeRepository urlRepo;

    @Autowired
    ClientConsumerRepository userRepo;

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
                float clickA = testDataRepository.countByTestNoAndPageType(test_no, "A")
                        - testDataRepository.countByTestNoAndPageTypeAndUrlNo(test_no, "A", null);
                float clickB = testDataRepository.countByTestNoAndPageType(test_no, "B")
                        - testDataRepository.countByTestNoAndPageTypeAndUrlNo(test_no, "B", null);

                float totalA = testDataRepository.countByTestNoAndPageTypeAndUrlNo(test_no, "A", null);
                float totalB = testDataRepository.countByTestNoAndPageTypeAndUrlNo(test_no, "B", null);

                analysis.setConversionA((float) (Math.round((clickA / totalA) * 1000) / 10.0));
                analysis.setConversionB((float) (Math.round((clickB / totalB) * 1000) / 10.0));

                analysis.setCon_rate((float) (Math.round(((clickB / totalB) - (clickA / totalA)) * 1000) / 10.0));

                // 이탈률
                analysis.setBounceA((float) (Math.round((1 - (clickA / totalA)) * 1000) / 10.0));
                analysis.setBounceB((float) (Math.round((1 - (clickB / totalB)) * 1000) / 10.0));
                analysis.setBo_rate(
                        (float) (Math.round(((1 - (clickB / totalB)) - (1 - (clickA / totalA))) * 1000) / 10.0));

                // 가입률
                totalA = testDataRepository.countByTestNoAndPageType(test.getTestNo(), "A");
                totalB = testDataRepository.countByTestNoAndPageType(test.getTestNo(), "B");
                // 이거 중복되는 유저 제거해서 세야함
                float joinA = testDataRepository.countByTestNoAndPageTypeAndSigned(test.getTestNo(), "A", true);
                float joinB = testDataRepository.countByTestNoAndPageTypeAndSigned(test.getTestNo(), "B", true);

                float tempA = 0;
                float tempB = 0;

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

        float cntA = testDataRepository.countByTestNoAndPageTypeAndUrlNo(testno, "A", null);
        float cntB = testDataRepository.countByTestNoAndPageTypeAndUrlNo(testno, "B", null);

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
            conversionA.add((float) (Math.round(A * 1000) / 10.0));
            conversionB.add((float) (Math.round(B * 1000) / 10.0));

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
