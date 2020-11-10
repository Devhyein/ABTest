package com.ssafy.free.service.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.free.dto.Admin.PageCnt;
import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Analysis.GraphData;
import com.ssafy.free.repository.PageCntRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.TestRepository;

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

}
