package com.ssafy.free.service.EventService;

import java.util.HashMap;

import com.ssafy.free.dto.sample.ClientConsumer;

public interface EventService {

    // 접속 기록 유무 확인
    public ClientConsumer searchRecord(String session_id, int test_no);

    // 유저 등록
    public ClientConsumer registUser(HashMap<String, Object> request);

    // 로그인 유저의 베이스 페이지 방문
    public int isSignedBase(HashMap<String, Object> request, int clientUserNo);

    // 비로그인 유저의 베이스 페이지 방문
    public int unSignedBase(HashMap<String, Object> request, int clientUserNo);

    // 로그인 유저의 이벤트 처리
    public int isSignedEvent(HashMap<String, Object> request,int clientUserNo);

    // 비로그인 유저의 이벤트 처리
    public int unSignedEvent(HashMap<String, Object> request,int clientUserNo);

}
