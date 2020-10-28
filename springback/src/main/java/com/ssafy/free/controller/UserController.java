package com.ssafy.free.controller;

import java.util.HashMap;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/account")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public Object createTest(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "회원가입 실패";
        response.data = null;
        
        try {
            int res = userService.join(request);
            if(res > 0) {
                response.status = true;
                response.msg = "success";
            } else if (res == -1){
                response.msg = "이미 존재하는 아이디";
            } else {
                response.data = "회원가입 실패";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  
}
