package com.ssafy.free.service.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.ssafy.free.dto.Admin.TestData;
import com.ssafy.free.dto.ClickEvent.User;
import com.ssafy.free.dto.sample.ClientConsumer;
import com.ssafy.free.repository.ClientConsumerRepository;
import com.ssafy.free.repository.TestDataRepository;
import com.ssafy.free.repository.UrlAttributeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    TestDataRepository testDataRepository;

    @Autowired
    ClientConsumerRepository clientConsumerRepository;

    @Autowired
    UrlAttributeRepository urlAttributeRepository;

    @Override
    public int isSignedBase(HashMap<String, Object> request, int clientUserNo) {
        log.info("======Enter the isSignedBase Service======");
        int ret = 0;
        try {
            int test_no = Integer.parseInt(request.get("test_no").toString());
            HashMap hashMap = (HashMap) request.get("user");
            User user = new User(hashMap.get("email").toString(), hashMap.get("gender").toString(),
                    Integer.parseInt(hashMap.get("age").toString()),
                    LocalDate.parse(hashMap.get("join_date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            // 넣을 data 생성
            TestData testData = new TestData();
            testData.setTestNo(test_no);
            testData.setUserNo(clientUserNo);
            testData.setPageType(request.get("page_type").toString());
            testData.setDate(
                    LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            testData.setSigned(Boolean.parseBoolean(request.get("signed").toString()));
            testData.setGender(user.getGender());
            testData.setAge(user.getAge());
            testData.setJoinDate(user.getJoin_date());

            // 데이터 삽입
            testDataRepository.save(testData);
            ret = 1;

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
    }

    @Override
    public int unSignedBase(HashMap<String, Object> request, int clientUserNo) {
        log.info("======Enter the isSignedBase Service======");
        int ret = 0;
        try {
            int test_no = Integer.parseInt(request.get("test_no").toString());

            // 넣을 data 생성
            TestData testData = new TestData();
            testData.setTestNo(test_no);
            testData.setUserNo(clientUserNo);
            testData.setPageType(request.get("page_type").toString());
            testData.setDate(
                    LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            testData.setSigned(Boolean.parseBoolean(request.get("signed").toString()));

            // 데이터 삽입
            testDataRepository.save(testData);
            ret = 1;

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
    }

    @Override
    public ClientConsumer searchRecord(String session_id, int test_no) {
        log.info("======Enter the searchRecord Service======");
        return clientConsumerRepository.findBySessionIdAndTestNo(session_id, test_no);
    }

    @Override
    public ClientConsumer registUser(HashMap<String, Object> request) {
        log.info("======Enter the registUser Service======");

        ClientConsumer clientConsumer = new ClientConsumer();

        clientConsumer.setSessionId(request.get("session_id").toString());
        clientConsumer.setTestNo(Integer.parseInt(request.get("test_no").toString()));
        clientConsumer.setPageType(request.get("page_type").toString());
        clientConsumer
                .setDate(LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return clientConsumerRepository.save(clientConsumer);
    }

    @Override
    public int isSignedEvent(HashMap<String, Object> request, int clientUserNo) {
        log.info("======Enter the isSignedEvent Service======");
        int ret = 0;
        try {
            String url = request.get("url").toString();
            int test_no = Integer.parseInt(request.get("test_no").toString());
            HashMap hashMap = (HashMap) request.get("user");
            User user = new User(hashMap.get("email").toString(), hashMap.get("gender").toString(),
                    Integer.parseInt(hashMap.get("age").toString()),
                    LocalDate.parse(hashMap.get("join_date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            // url_no 찾기
            int url_no = (urlAttributeRepository.findByUrlNameAndTestNo(url, test_no)).getUrlNo();

            // 넣을 data 생성
            TestData testData = new TestData();
            testData.setUrlNo(url_no);
            testData.setTestNo(test_no);
            testData.setUserNo(clientUserNo);
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
            e.printStackTrace();
            return ret;
        }
    }

    @Override
    public int unSignedEvent(HashMap<String, Object> request, int clientUserNo) {
        log.info("======Enter the unSignedEvent Service======");
        int ret = 0;
        try {
            String url = request.get("url").toString();
            int test_no = Integer.parseInt(request.get("test_no").toString());

            // url_no 찾기
            int url_no = (urlAttributeRepository.findByUrlNameAndTestNo(url, test_no)).getUrlNo();

            // 넣을 data 생성
            TestData testData = new TestData();
            testData.setUrlNo(url_no);
            testData.setTestNo(test_no);
            testData.setUserNo(clientUserNo);
            testData.setPageType(request.get("page_type").toString());
            testData.setDate(
                    LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            testData.setSigned(Boolean.parseBoolean(request.get("signed").toString()));

            // 데이터 삽입
            testDataRepository.save(testData);
            ret = 1;

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
    }

}
