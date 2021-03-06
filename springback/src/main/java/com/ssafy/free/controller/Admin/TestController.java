package com.ssafy.free.controller.Admin;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.Analysis.Analysis;
import com.ssafy.free.dto.Analysis.AnalysisConversionWithUrl;
import com.ssafy.free.dto.Analysis.TableDataAge;
import com.ssafy.free.dto.Analysis.TableDataGender;
import com.ssafy.free.service.AdminService.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, allowCredentials = "true")
@RestController
@RequestMapping("/spring")
public class TestController {
    @Autowired
    TestService testService;

    @ApiOperation(value = "테스트 상세조회 - 전체")
    @GetMapping("/detail/test")
    public Object getDetailTest(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "테스트 상세 조회 실패";
        response.data = null;

        try {
            Analysis detail = testService.getDetailTest(test_no);

            if (detail != null) {
                response.status = true;
                response.msg = "success";
                response.data = detail;
            } else {
                response.status = false;
                response.msg = "해당 번호의 테스트가 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "테스트 상세조회 - 성별")
    @GetMapping("/detail/gender")
    public Object getDetailTestGender(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "테스트 상세 조회 실패 - 성별";
        response.data = null;

        try {
            Analysis detail = testService.getDetailTestGender(test_no);

            if (detail != null) {
                response.status = true;
                response.msg = "success";
                response.data = detail;
            } else {
                response.status = false;
                response.msg = "해당 번호의 테스트가 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "테스트 상세조회 - url별 전환률")
    @GetMapping("/detail/conversion/url")
    public Object getDetailTestConversionWithUrl(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "테스트 상세 조회 실패";
        response.data = null;

        try {
            AnalysisConversionWithUrl detail = testService.getDetailTestConversionWithUrl(test_no);
            if (detail != null) {
                response.status = true;
                response.msg = "success";
                response.data = detail;
            } else {
                response.status = false;
                response.msg = "해당 번호의 테스트가 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "성별 테이블 데이터")
    @GetMapping("/detail/table/gender/test")
    public Object getTableDataGender(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "테스트 상세 조회 실패";
        response.data = null;

        try {
            TableDataGender detail = testService.getTableDataGender(test_no);
            if (detail != null) {
                response.status = true;
                response.msg = "success";
                response.data = detail;
            } else {
                response.status = false;
                response.msg = "해당 번호의 테스트가 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "연령별 테이블 데이터")
    @GetMapping("/detail/table/age/test")
    public Object getTableDataAge(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "테스트 상세 조회 실패";
        response.data = null;

        try {
            TableDataAge detail = testService.getTableDataAge(test_no);
            if (detail != null) {
                response.status = true;
                response.msg = "success";
                response.data = detail;
            } else {
                response.status = false;
                response.msg = "해당 번호의 테스트가 없습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
