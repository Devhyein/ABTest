package com.ssafy.free.controller;

import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.Test;
import com.ssafy.free.service.ManageService;

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
@RequestMapping("/spring")
public class ManageController {
    
    @Autowired
    ManageService manageService;

    @ApiOperation(value = "새로운 테스트 생성")
    @PostMapping("/createtest")
    public Object createTest(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "test 생성 실패";
        response.data = null;
        
        try { 
            int res = manageService.createTest(request);
            if(res>0){
                response.status = true;
                response.msg = "success";
            } else {
                System.out.println("테스트 저장 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  

    @ApiOperation(value = "관리자의 테스트 목록 호출")
    @GetMapping("/testlist")
    public Object getTestList(@RequestParam String email) {
        final RestResponse response = new RestResponse();
        
        response.status = false;
        response.msg = "test 목록 조회 실패";
        response.data = null;
        
        try { 
            List<Test> testList = manageService.getTestList(email);
            if(!testList.isEmpty()){
                response.status = true;
                response.msg = "success";
                response.data = testList;
            } else {
                response.status = true;
                response.msg = "목록이 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }  
}
