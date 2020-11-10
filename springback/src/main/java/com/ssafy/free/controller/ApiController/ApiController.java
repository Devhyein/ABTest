package com.ssafy.free.controller.ApiController;

import javax.servlet.http.HttpSession;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.Api.Context;
import com.ssafy.free.service.ApiService.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @ApiOperation(value = "A or B 할당")
    @GetMapping("/assign")
    public Object getTestList(HttpSession session, String url) {
        final RestResponse response = new RestResponse();
        Context ctx = new Context();
        log.info("url : " + url);
        try {
            ctx.setSession_id(session.getId());
            Context ret = apiService.convert(ctx, url);
            
            if (ret.getPage_type() == null) {
                ret.setPage_type("A");
                response.status = true;
                response.msg = "success but end or pre test";
                response.data = ret;
                return response;
            }

            response.status = true;
            response.msg = "Success";
            response.data = ret;

        } catch (Exception e) {
            response.status = false;
            response.msg = "Fail to produce A/B";
        }

        return response;
    }
}
