package com.ssafy.free.service.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Admin.PageCnt;
import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Analysis.GraphData;
import com.ssafy.free.dto.Analysis.GraphDataGender;
import com.ssafy.free.repository.BuyerRepository;
import com.ssafy.free.repository.PageCntRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;
import com.ssafy.free.repository.UserRepository;
import com.ssafy.free.repository.UserSampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    PageCntRepository pageRepo;

    @Autowired
    TestDataRepository testDataRepository;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserSampleRepository userSampleRepo;

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
                // 전체 페이지 제공 회수
                List<PageCnt> pagecnt = pageRepo.findByTestNoOrderByDateAsc(test_no);
                for (PageCnt page : pagecnt) {
                    // 각 날짜의 전체 페이지 제공 횟수
                    LocalDate pageDate = page.getDate();
                    while (!start.equals(pageDate)) {
                        AChartData.add((float) 0);
                        BChartData.add((float) 0);
                        date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                        start = start.plusDays(1);
                        // System.out.println(LocalDate.parse(start.toString(),
                        // DateTimeFormatter.ofPattern("MM-dd")));
                    }
                    float cntA = page.getCntA();
                    float cntB = page.getCntB();

                    // 각 날짜의 전체 페이지 전환 횟수
                    float clickA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", pageDate);
                    float clickB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", pageDate);
                    float A = clickA / cntA;
                    AChartData.add((float) (Math.round(A * 1000) / 1000.0) * 100);
                    float B = clickB / cntB;
                    BChartData.add((float) (Math.round(B * 1000) / 1000.0) * 100);
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                    start = start.plusDays(1);
                }
                start = start.minusDays(1);
                while (!start.equals(test.getEnd()) && !start.equals(today)) {
                    AChartData.add((float) 0);
                    BChartData.add((float) 0);
                    start = start.plusDays(1);
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
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
                // 전체 페이지 제공 회수
                List<PageCnt> pagecnt = pageRepo.findByTestNoOrderByDateAsc(test_no);
                for (PageCnt page : pagecnt) {
                    // 각 날짜의 전체 페이지 제공 횟수
                    LocalDate pageDate = page.getDate();
                    while (!start.equals(pageDate)) {
                        AChartData.add((float) 100);
                        BChartData.add((float) 100);
                        date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                        start = start.plusDays(1);
                    }
                    float cntA = page.getCntA();
                    float cntB = page.getCntB();

                    // 각 날짜의 전체 페이지 전환 횟수
                    float clickA = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "A", pageDate);
                    float clickB = testDataRepository.countByTestNoAndPageTypeAndDate(test_no, "B", pageDate);
                    float A = clickA / cntA;
                    AChartData.add((float) (Math.round((1 - A) * 1000) / 10.0));
                    float B = clickB / cntB;
                    BChartData.add((float) (Math.round((1 - B) * 1000) / 10.0));
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
                    start = start.plusDays(1);
                }
                start = start.minusDays(1);
                while (!start.equals(test.getEnd()) && !start.equals(today)) {
                    AChartData.add((float) 100);
                    BChartData.add((float) 100);
                    start = start.plusDays(1);
                    date.add(start.getMonthValue() + "/" + start.getDayOfMonth());
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
                    float userA = userRepo.countByTestNoAndPageTypeAndDate(test_no, "A", start);
                    float userB = userRepo.countByTestNoAndPageTypeAndDate(test_no, "B", start);

                    // 가입률
                    float joinUserA = userSampleRepo.countByTestNoAndPageTypeAndJoinDate(test_no, "A", start);
                    float joinUserB = userSampleRepo.countByTestNoAndPageTypeAndJoinDate(test_no, "B", start);

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
        float numAFemale = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "여성");
        float numAMale = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "A", "남성");
        float cntA = testDataRepository.countByTestNoAndPageTypeAndIsJoined(test_no, "A", true);

        float numBFemale = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "여성");
        float numBMale = testDataRepository.countByTestNoAndPageTypeAndGender(test_no, "B", "남성");
        float cntB = testDataRepository.countByTestNoAndPageTypeAndIsJoined(test_no, "B", true);

        data.setAFemaleChartData((float) (Math.round((numAFemale / cntA) * 1000) / 10.0));
        data.setAMaleChartData((float) (Math.round((numAMale / cntA) * 1000) / 10.0));
        data.setBFemaleChartData((float) (Math.round((numBFemale / cntB) * 1000) / 10.0));
        data.setBMaleChartData((float) (Math.round((numBMale / cntB) * 1000) / 10.0));

        return data;
    }

}
