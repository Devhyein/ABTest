package com.ssafy.free.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @ApiOperation(value = "A or B 할당")
    @GetMapping("/assign")
    public Object getTestList(HttpSession session) {
        final RestResponse response = new RestResponse();
        apiService.convert(session.getId());

        response.status = false;
        response.msg = "Fail to produce A/B ";
        response.data = null;

        try {

        } catch (Exception e) {

        }

        return response;
    }
}
