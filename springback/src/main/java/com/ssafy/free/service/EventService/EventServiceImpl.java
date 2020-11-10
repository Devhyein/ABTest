package com.ssafy.free.service.EventService;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    public int registEvent(HashMap<String, Object> request) {

        int ret = 0;
        log.info("ret : " + ret);
        try {
            return ret;
        } catch (Exception e) {
            return ret;
        }
    }
}
