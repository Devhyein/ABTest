package com.ssafy.free.controller.Admin;

import java.util.HashMap;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.sample.ClientConsumer;
import com.ssafy.free.service.EventService.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "*" }, allowCredentials = "true")
@RestController
@RequestMapping("/spring")
public class EventController {
    @Autowired
    EventService eventService;

    @ApiOperation(value = "클릭 이벤트 컨트롤러")
    @PostMapping("/event/click")
    public Object createTest(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();
        String session_id = request.get("session_id").toString();
        int test_no = Integer.parseInt(request.get("test_no").toString());

        // 접속 기록 유무 확인
        ClientConsumer clientConsumer = eventService.searchRecord(session_id, test_no);

        // 기록이 없는 경우
        if (clientConsumer == null) {
            try {
                // 등록
                clientConsumer = eventService.registUser(request);
            } catch (Exception e) {
                response.status = false;
                response.msg = "Fail to first regist User";
                return response;
            }
        }

        // Client Consumer의 User_no 얻기
        int clientUserNo = clientConsumer.getUserNo();

        // Main 접속
        if (request.get("url") == null) {
            try {
                // 로그인 여부
                boolean signed = Boolean.parseBoolean(request.get("signed").toString());
                log.info("s= : "+signed+"");

                int ret = 0;
                // 로그인한 회원인 경우
                if (signed) {
                    ret = eventService.isSignedBase(request, clientUserNo);
                }
                // 비로그인 회원인 경우
                else {
                    ret = eventService.unSignedBase(request, clientUserNo);
                }

                if (ret == 1) {
                    response.status = true;
                    response.msg = "success to regist at basepage";
                } else {
                    response.status = false;
                    response.msg = "Fail to regist at basepage";
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.status = false;
                response.msg = "Fail to access service";
            }
        } else {
            // 다른 페이지로 이동
            try {
                // 로그인 여부
                boolean signed = Boolean.parseBoolean(request.get("signed").toString());

                int ret = 0;
                // 로그인한 회원인 경우
                if (signed) {
                    ret = eventService.isSignedEvent(request, clientUserNo);
                }
                // 비로그인 회원인 경우
                else {
                    ret = eventService.unSignedEvent(request, clientUserNo);
                }

                if (ret == 1) {
                    response.status = true;
                    response.msg = "success";
                } else {
                    response.status = false;
                    response.msg = "Fail to event regist";
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.status = false;
                response.msg = "Fail to access service";
            }
        }
        return response;
    }
}
