package com.ssafy.free.controller;

import java.util.HashMap;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Object join(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "회원가입 실패";
        response.data = null;
        
        try {
            int res = userService.join(request);
            if(res > 0) {
                response.status = true;
                response.msg = "success";
            } else {
                response.data = "회원가입 실패";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  

    @ApiOperation(value = "아이디 중복 체크")
    @GetMapping("/checkId")
    public Object getTestList(@RequestParam String id) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "중복 체크 실패";
        response.data = null;
        
        try { 
            int res = userService.checkId(id);
           
            if(res>0){
                response.status = true;
                response.msg = "success";
            } else {
                response.status = false;
                response.msg = "이미 존재하는 아이디";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public Object login(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "로그인 실패";
        response.data = null;
        
        try {
            String data = userService.login(request);
            if(data!=null){
                response.status = true;
                response.msg = "success";
                response.data = data;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  
}
