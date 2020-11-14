package com.ssafy.free.controller.Admin;

import java.util.HashMap;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.service.EventService.ClickEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, allowCredentials = "true")
@RestController
@RequestMapping("/spring")
public class ClickEventController {
    @Autowired
    ClickEventService eventService;

    @ApiOperation(value = "클릭 이벤트 컨트롤러")
    @PostMapping("/event/click")
    public Object createTest(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();

        try {
            int ret = eventService.registEvent(request);
            if (ret == 1) {
                response.status = true;
                response.msg = "success";
            } else {
                response.status = false;
                response.msg = "Fail to event regist";
            }
        } catch (Exception e) {
            response.status = false;
            response.msg = "Fail to access service";
        }

        return response;
    }
}
