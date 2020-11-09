package com.ssafy.free.controller.Admin;

import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.Admin.TestResponse;
import com.ssafy.free.service.ManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            if (res > 0) {
                response.status = true;
                response.msg = "success";
            } else if (res == -1) {
                response.msg = "URL A 중복";
            } else if (res == -2) {
                response.msg = "URL B 중복";
            } else {
                System.out.println("테스트 저장 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "테스트 수정")
    @PutMapping("/modify/test")
    public Object modifyTest(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "test 수정 실패";
        response.data = null;

        try {
            int res = manageService.modifyTest(request);
            if (res > 0) {
                response.status = true;
                response.msg = "success";
            } else {
                System.out.println("테스트 수정 실패");
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
            List<TestResponse> testList = manageService.getTestList(email);
            if (!testList.isEmpty()) {
                response.status = true;
                response.msg = "success";
                response.data = testList;
            } else {
                response.status = false;
                response.msg = "목록이 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "진행전 테스트 목록 호출")
    @GetMapping("/testlist/before")
    public Object getTestListBefore(@RequestParam String email) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "test 목록 조회 실패";
        response.data = null;

        try {
            List<TestResponse> testList = manageService.getTestListBefore(email);
            if (!testList.isEmpty()) {
                response.status = true;
                response.msg = "success";
                response.data = testList;
            } else {
                response.status = false;
                response.msg = "목록이 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "진행중 테스트 목록 호출")
    @GetMapping("/testlist/progress")
    public Object getTestListProgress(@RequestParam String email) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "test 목록 조회 실패";
        response.data = null;

        try {
            List<TestResponse> testList = manageService.getTestListProgress(email);
            if (!testList.isEmpty()) {
                response.status = true;
                response.msg = "success";
                response.data = testList;
            } else {
                response.status = false;
                response.msg = "목록이 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "진행후 테스트 목록 호출")
    @GetMapping("/testlist/complete")
    public Object getTestListComplete(@RequestParam String email) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "test 목록 조회 실패";
        response.data = null;

        try {
            List<TestResponse> testList = manageService.getTestListComplete(email);
            if (!testList.isEmpty()) {
                response.status = true;
                response.msg = "success";
                response.data = testList;
            } else {
                response.status = false;
                response.msg = "목록이 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "테스트 삭제")
    @DeleteMapping("/delete/test")
    public Object deleteTest(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "test 조회 실패";
        response.data = null;

        try {
            int res = manageService.deleteTest(test_no);
            if (res > 0) {
                response.status = true;
                response.msg = "success";
                response.data = null;
            } else {
                response.status = false;
                response.msg = "삭제에 실패하였습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "관리자 로그인")
    @PostMapping("/login")
    public Object login(@RequestBody HashMap<String, Object> request) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "로그인 실패";
        response.data = null;

        try {
            String data = manageService.login(request);
            if (data != null) {
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
