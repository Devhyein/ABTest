package com.ssafy.free.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Admin.UrlAttribute;
import com.ssafy.free.dto.Analysis.Analysis;
import com.ssafy.free.dto.Analysis.AnalysisConversionWithUrl;
import com.ssafy.free.dto.Analysis.TableDataAge;
import com.ssafy.free.dto.Analysis.TableDataGender;
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

    @Override
    public TableDataGender getTableDataGender(int test_no) {
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                TableDataGender analysis = new TableDataGender(test);

                // MALE 전환율
                float clickA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "남성")
                        - testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "남성", null);
                float clickB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "남성")
                        - testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "남성", null);

                float totalA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "남성", null);
                float totalB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "남성", null);

                analysis.setConversionAMale((float) (Math.round((clickA / totalA) * 1000) / 10.0));
                analysis.setConversionBMale((float) (Math.round((clickB / totalB) * 1000) / 10.0));

                analysis.setCon_rateMale((float) (Math.round(((clickB / totalB) - (clickA / totalA)) * 1000) / 10.0));

                // MALE 이탈률
                analysis.setBounceAMale((float) (Math.round((1 - (clickA / totalA)) * 1000) / 10.0));
                analysis.setBounceBMale((float) (Math.round((1 - (clickB / totalB)) * 1000) / 10.0));
                analysis.setBo_rateMale(
                        (float) (Math.round(((1 - (clickB / totalB)) - (1 - (clickA / totalA))) * 1000) / 10.0));

                // FEMALE 전환율
                clickA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "여성")
                        - testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "여성", null);
                clickB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "여성")
                        - testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "여성", null);

                totalA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "여성", null);
                totalB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "여성", null);

                analysis.setConversionAFemale((float) (Math.round((clickA / totalA) * 1000) / 10.0));
                analysis.setConversionBFemale((float) (Math.round((clickB / totalB) * 1000) / 10.0));

                analysis.setCon_rateFemale((float) (Math.round(((clickB / totalB) - (clickA / totalA)) * 1000) / 10.0));

                // FEMALE 이탈률
                analysis.setBounceAFemale((float) (Math.round((1 - (clickA / totalA)) * 1000) / 10.0));
                analysis.setBounceBFemale((float) (Math.round((1 - (clickB / totalB)) * 1000) / 10.0));
                analysis.setBo_rateFemale(
                        (float) (Math.round(((1 - (clickB / totalB)) - (1 - (clickA / totalA))) * 1000) / 10.0));

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
    public TableDataAge getTableDataAge(int test_no) {
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                TableDataAge analysis = new TableDataAge(test);

                List<Float> aData = new ArrayList<>();
                List<Float> bData = new ArrayList<>();
                List<Float> bounceA = new ArrayList<>();
                List<Float> bounceB = new ArrayList<>();
                List<Float> con_rate = new ArrayList<>();
                List<Float> bo_rate = new ArrayList<>();

                // 연령별 전환율
                for (int age = 20; age <= 60; age += 10) {
                    float A = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "A", age, null);
                    float totalA = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "A", age);
                    float upA = totalA - A;

                    float B = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "B", age, null);
                    float totalB = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "B", age);
                    float upB = totalB - B;
                    // 전환율
                    aData.add((float) (Math.round((upA / A) * 1000) / 10.0));
                    bData.add((float) (Math.round((upB / B) * 1000) / 10.0));
                    con_rate.add((float) (Math.round(((upB / B) - (upA / A)) * 1000) / 10.0));
                    // 이탈률
                    bounceA.add((float) (Math.round((1 - (upA / A)) * 1000) / 10.0));
                    bounceB.add((float) (Math.round((1 - (upB / B)) * 1000) / 10.0));
                    bo_rate.add((float) (Math.round(((1 - (upB / B)) - (1 - (upA / A))) * 1000) / 10.0));
                }
                analysis.setConversionA(aData);
                analysis.setConversionB(bData);
                analysis.setBounceA(bounceA);
                analysis.setBounceB(bounceB);
                analysis.setCon_rate(con_rate);
                analysis.setBo_rate(bo_rate);

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
