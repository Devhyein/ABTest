package com.ssafy.free.controller.SampleControlloer;

import java.util.HashMap;
import java.util.Optional;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.sample.UserSample;
import com.ssafy.free.service.SampleService.UserService;

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
@RequestMapping("/spring/account")
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
            if (res > 0) {
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
    public Object checkId(@RequestParam String id) {
        final RestResponse response = new RestResponse();
        response.status = false;
        response.msg = "중복 체크 실패";
        response.data = null;

        try {
            int res = userService.checkId(id);
            response.status = true;
            response.msg = "success";
            if (res > 0) {
                response.data = true;
            } else {
                response.data = false;
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
            Optional<UserSample> usersample = userService.login(request);
            if (usersample.isPresent()) {
                HashMap<String, Object> data = new HashMap<>();
                HashMap<String, Object> user = new HashMap<>();
                user.put("email", usersample.get().getEmail());
                user.put("gender", usersample.get().getGender());
                user.put("age", usersample.get().getAge());
                user.put("join_date", usersample.get().getJoinDate());
                data.put("user", user);
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
