package com.ssafy.free.service.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Analysis.GraphData;
import com.ssafy.free.dto.Analysis.GraphDataAge;
import com.ssafy.free.dto.Analysis.GraphDataGender;
import com.ssafy.free.repository.BuyerRepository;
import com.ssafy.free.repository.ClientConsumerRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestDataRepository testDataRepository;

    @Autowired
    ClientConsumerRepository userRepo;

    @Autowired
    BuyerRepository buyerRepo;

    @Override
    public GraphData getChartConversion(int test_no) {
        GraphData data = new GraphData();
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                LocalDate start = test.getStart();
                LocalDate today = LocalDate.parse(LocalDate.now().toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                // 전체 전환율 계산
                List<Float> AChartData = new ArrayList<Float>();
                List<Float> BChartData = new ArrayList<Float>();
                List<String> date = new ArrayList<String>();

                // 각 날짜의 전체 페이지 제공 횟수
                while (!start.minusDays(1).equals(test.getEnd()) && !start.minusDays(1).equals(today)) {
                    float totalA = testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "A", start,
                            null);
                    float totalB = testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "B", start,
                            null);

                    if (totalA == 0) {
                        AChartData.add((float) 0);
                    } else {
                        float clickA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", start)
                                - testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "A", start, null);
                        float A = clickA / totalA;
                        AChartData.add((float) (Math.round(A * 1000) / 1000.0) * 100);
                    }
                    if (totalB == 0)
                        BChartData.add((float) 0);
                    else {
                        float clickB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", start)
                                - testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "B", start, null);
                        float B = clickB / totalB;
                        BChartData.add((float) (Math.round(B * 1000) / 1000.0) * 100);
                    }
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                    start = start.plusDays(1);
                }

                data.setAChartData(AChartData);
                data.setBChartData(BChartData);
                data.setDate(date);
                return data;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public GraphData getChartBounce(int test_no) {
        GraphData data = new GraphData();
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                LocalDate start = test.getStart();
                LocalDate today = LocalDate.parse(LocalDate.now().toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                // 전체 전환율 계산
                List<Float> AChartData = new ArrayList<Float>();
                List<Float> BChartData = new ArrayList<Float>();
                List<String> date = new ArrayList<String>();

                // 각 날짜의 전체 페이지 제공 횟수
                while (!start.minusDays(1).equals(test.getEnd()) && !start.minusDays(1).equals(today)) {
                    float totalA = testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "A", start,
                            null);
                    float totalB = testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "B", start,
                            null);
                    if (totalA == 0) {
                        AChartData.add((float) 0);
                    } else {
                        float clickA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", start)
                                - testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "A", start, null);
                        float A = clickA / totalA;
                        AChartData.add((float) (Math.round((1 - A) * 1000) / 1000.0) * 100);
                    }
                    if (totalB == 0)
                        BChartData.add((float) 0);
                    else {
                        float clickB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", start)
                                - testDataRepository.countByTestNoAndPageTypeAndDateAndUrlNo(test_no, "B", start, null);
                        float B = clickB / totalB;
                        BChartData.add((float) (Math.round((1 - B) * 1000) / 1000.0) * 100);
                    }
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                    start = start.plusDays(1);
                }

                data.setAChartData(AChartData);
                data.setBChartData(BChartData);
                data.setDate(date);
                return data;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public GraphData getChartJoin(int test_no) {
        GraphData data = new GraphData();
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                LocalDate start = test.getStart();
                LocalDate today = LocalDate.parse(LocalDate.now().toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // 전체 전환율 계산
                List<Float> AChartData = new ArrayList<Float>();
                List<Float> BChartData = new ArrayList<Float>();
                List<String> date = new ArrayList<String>();

                while (!start.minusDays(1).equals(test.getEnd()) && !start.minusDays(1).equals(today)) {
                    float userA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", start);
                    float userB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", start);

                    // 가입률
                    // 중복되는 유저 제거하고 세야함
                    float joinUserA = testDataRepository.countByTestNoAndPageTypeAndSignedAndJoinDate(test.getTestNo(),
                            "A", true, start);
                    float joinUserB = testDataRepository.countByTestNoAndPageTypeAndSignedAndJoinDate(test.getTestNo(),
                            "B", true, start);
                    AChartData.add((float) (Math.round((joinUserA / userA) * 1000) / 1000.0) * 100);
                    BChartData.add((float) (Math.round((joinUserB / userB) * 1000) / 1000.0) * 100);
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());

                    start = start.plusDays(1);
                }

                data.setAChartData(AChartData);
                data.setBChartData(BChartData);
                data.setDate(date);

                return data;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphData getChartPurchase(int test_no) {
        GraphData data = new GraphData();
        try {
            Test test = testRepository.getOne(test_no);
            if (test != null) {
                LocalDate start = test.getStart();
                LocalDate today = LocalDate.parse(LocalDate.now().toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // 전체 전환율 계산
                List<Float> AChartData = new ArrayList<Float>();
                List<Float> BChartData = new ArrayList<Float>();
                List<String> date = new ArrayList<String>();

                while (!start.minusDays(1).equals(test.getEnd()) && !start.minusDays(1).equals(today)) {
                    float userA = userRepo.countByTestNoAndPageTypeAndDate(test_no, "A", start);
                    float userB = userRepo.countByTestNoAndPageTypeAndDate(test_no, "B", start);

                    // 구매율
                    float purUserA = buyerRepo.countByTestNoAndPageTypeAndDate(test_no, "A", start);
                    float purUserB = buyerRepo.countByTestNoAndPageTypeAndDate(test_no, "B", start);

                    AChartData.add((float) (Math.round((purUserA / userA) * 1000) / 1000.0) * 100);
                    BChartData.add((float) (Math.round((purUserB / userB) * 1000) / 1000.0) * 100);

                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());

                    start = start.plusDays(1);
                }

                data.setAChartData(AChartData);
                data.setBChartData(BChartData);
                data.setDate(date);

                return data;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphDataGender getChartGenderConversion(int test_no) {
        GraphDataGender data = new GraphDataGender();
        // 근데 가입 한 유저가 로그인 안한 상태로 클릭 이벤트를 발생시키면 우리는 세션아이디를 유저 샘플 디비에
        // 저장하지 않는 이상 이 사람이 가입 한 유저인지 모르기때문에 성별, 나이를 모르지 않나?

        // 현재 분모는 가입 한 유저. 이거 전체 유저로 분모를 잡을 건지 논의 필요
        try {
            float maleA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "남성", null);
            float totalMaleA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "남성");
            // 분자
            float up = totalMaleA - maleA;
            data.setAMaleChartData((float) (Math.round((up / maleA) * 1000) / 10.0));

            float maleB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "남성", null);
            float totalMaleB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "남성");
            up = totalMaleB - maleB;
            data.setBMaleChartData((float) (Math.round((up / maleB) * 1000) / 10.0));

            float femaleA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "여성", null);
            float totalFemaleA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "여성");
            up = totalFemaleA - femaleA;
            data.setAFemaleChartData((float) (Math.round((up / femaleA) * 1000) / 10.0));

            float femaleB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "여성", null);
            float totalFemaleB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "여성");
            up = totalFemaleB - femaleB;
            data.setBFemaleChartData((float) (Math.round((up / femaleB) * 1000) / 10.0));

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphDataGender getChartGenderBounce(int test_no) {
        GraphDataGender data = new GraphDataGender();

        try {
            float maleA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "남성", null);
            float totalMaleA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "남성");
            // 분자
            float up = totalMaleA - maleA;
            data.setAMaleChartData((float) (Math.round((1 - (up / maleA)) * 1000) / 10.0));

            float maleB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "남성", null);
            float totalMaleB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "남성");
            up = totalMaleB - maleB;
            data.setBMaleChartData((float) (Math.round((1 - (up / maleB)) * 1000) / 10.0));

            float femaleA = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "A", "여성", null);
            float totalFemaleA = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "여성");
            up = totalFemaleA - femaleA;
            data.setAFemaleChartData((float) (Math.round((1 - (up / femaleA)) * 1000) / 10.0));

            float femaleB = testDataRepository.countByTestNoAndPageTypeAndGenderAndUrlNo(test_no, "B", "여성", null);
            float totalFemaleB = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "여성");
            up = totalFemaleB - femaleB;
            data.setBFemaleChartData((float) (Math.round((1 - (up / femaleB)) * 1000) / 10.0));

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphDataAge getChartAgeConversion(int test_no) {
        GraphDataAge data = new GraphDataAge();

        List<Float> aData = new ArrayList<>();
        List<Float> bData = new ArrayList<>();

        try {
            for (int age = 20; age <= 60; age += 10) {
                float A = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "A", age, null);
                float totalA = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "A", age);
                float upA = totalA - A;

                float B = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "B", age, null);
                float totalB = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "B", age);
                float upB = totalB - B;
                aData.add((float) (Math.round((upA / A) * 1000) / 10.0) * (-1));
                bData.add((float) (Math.round((upB / B) * 1000) / 10.0));
            }
            data.setAChartData(aData);
            data.setBChartData(bData);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphDataAge getChartAgeBounce(int test_no) {
        GraphDataAge data = new GraphDataAge();

        List<Float> aData = new ArrayList<>();
        List<Float> bData = new ArrayList<>();

        try {
            for (int age = 20; age <= 60; age += 10) {
                float A = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "A", age, null);
                float totalA = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "A", age);
                float upA = totalA - A;

                float B = testDataRepository.countByTestNoAndPageTypeAndAgeAndUrlNo(test_no, "B", age, null);
                float totalB = testDataRepository.countByTestNoAndPageTypeAndAge(test_no, "B", age);
                float upB = totalB - B;
                aData.add((float) (Math.round((1 - (upA / A)) * 1000) / 10.0) * (-1));
                bData.add((float) (Math.round((1 - (upB / B)) * 1000) / 10.0));
            }
            data.setAChartData(aData);
            data.setBChartData(bData);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
