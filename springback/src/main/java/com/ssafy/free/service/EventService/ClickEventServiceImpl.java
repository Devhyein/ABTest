package com.ssafy.free.service.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.ssafy.free.dto.Admin.TestData;
import com.ssafy.free.dto.ClickEvent.User;
import com.ssafy.free.repository.ClientConsumerRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.UrlAttributeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClickEventServiceImpl implements ClickEventService {

    @Autowired
    TestDataRepository testDataRepository;

    @Autowired
    ClientConsumerRepository clientConsumerRepository;

    @Autowired
    UrlAttributeRepository urlAttributeRepository;

    public int registEvent(HashMap<String, Object> request) {
        int ret = 0;
        try {
            System.out.println("start");
            String session_id = request.get("session_id").toString();
            String url = request.get("url").toString();
            int test_no = Integer.parseInt(request.get("test_no").toString());
            HashMap hashMap = (HashMap) request.get("user");
            User user = new User(hashMap.get("email").toString(), hashMap.get("gender").toString(),
                    Integer.parseInt(hashMap.get("age").toString()),
                    LocalDate.parse(hashMap.get("join_date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            // url_no 찾기
            int url_no = (urlAttributeRepository.findByUrlNameAndTestNo(url, test_no)).getUrlNo();

            // user_no 탐색
            int user_no = (clientConsumerRepository.findBySessionIdAndTestNo(session_id, test_no)).getUserNo();

            // 넣을 data 생성
            TestData testData = new TestData();
            testData.setUrlNo(url_no);
            testData.setTestNo(test_no);
            testData.setUserNo(user_no);
            testData.setPageType(request.get("page_type").toString());
            testData.setDate(
                    LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            testData.setSigned(Boolean.parseBoolean(request.get("signed").toString()));
            if (Boolean.parseBoolean(request.get("signed").toString())) {
                testData.setGender(user.getGender());
                testData.setAge(user.getAge());
                testData.setJoinDate(user.getJoin_date());
            }

            // 데이터 삽입
            testDataRepository.save(testData);
            ret = 1;

            return ret;
        } catch (Exception e) {
            return ret;
        }
    }
}
