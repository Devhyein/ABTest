package com.ssafy.free.service.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.ssafy.free.dto.Admin.UrlAttribute;
import com.ssafy.free.dto.ClickEvent.ClickData;
import com.ssafy.free.dto.ClickEvent.User;
import com.ssafy.free.repository.ClientConsumerRepository;
import com.ssafy.free.repository.UrlAttributeRepository;
import com.ssafy.free.repository.EventRepository.ClickEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClickEventServiceImpl implements ClickEventService {

    @Autowired
    ClickEventRepository clickEventRepository;

    @Autowired
    ClientConsumerRepository clientConsumerRepository;

    @Autowired
    UrlAttributeRepository urlAttributeRepository;

    public int registEvent(HashMap<String, Object> request) {
        int ret = 0;
        try {
            String session_id = request.get("session_id").toString();
            String url = request.get("url").toString();
            User user = (User) request.get("user");

            // url_no 찾기
            int url_no = (urlAttributeRepository.findByUrlName(url)).getUrlNo();

            // user_no 탐색
            int user_no = (clientConsumerRepository.findBySessionID(session_id)).getUserNo();

            ClickData clickData = new ClickData();
            clickData.setUrl_no(url_no);
            clickData.setTest_no(Integer.parseInt(request.get("test_no").toString()));
            clickData.setUser_no(user_no);
            clickData.setPage_type(request.get("page_type").toString());
            clickData.setDate(
                    LocalDate.parse(request.get("date").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            clickData.setSigned(Boolean.parseBoolean(request.get("singed").toString()));
            clickData.setGender(user.getGender());
            clickData.setAge(user.getAge());
            clickData.setJoin_date(user.getJoin_date());

            // 클릭 된 데이터와 일치하는 url찾기
            clickEventRepository.save(clickData);
            ret = 1;

            return ret;
        } catch (Exception e) {
            return ret;
        }
    }
}
