package com.ssafy.free.controller.Admin;

import com.ssafy.free.dto.RestResponse;
import com.ssafy.free.dto.Admin.TestResponse;
import com.ssafy.free.dto.Analysis.GraphData;
import com.ssafy.free.dto.Analysis.GraphDataGender;
import com.ssafy.free.service.ManageService;
import com.ssafy.free.service.Test.GraphService;

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
public class GraphController {

    @Autowired
    GraphService graphService;

    @ApiOperation(value = "그래프 조회 - 전체전환율")
    @GetMapping("/chart/test/conversion")
    public Object getChartConversion(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "전환율 조회 실패";
        response.data = null;

        try {
            GraphData data = graphService.getChartConversion(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "그래프 조회 - 전체이탈률")
    @GetMapping("/chart/test/bounce")
    public Object getChartBounce(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "이탈률 조회 실패";
        response.data = null;

        try {
            GraphData data = graphService.getChartBounce(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "그래프 조회 - 전체가입률")
    @GetMapping("/chart/test/join")
    public Object getChartJoin(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "이탈률 조회 실패";
        response.data = null;

        try {
            GraphData data = graphService.getChartJoin(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "그래프 조회 - 전체구매율")
    @GetMapping("/chart/test/purchase")
    public Object getChartPurchase(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "이탈률 조회 실패";
        response.data = null;

        try {
            GraphData data = graphService.getChartPurchase(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "그래프 조회 - 성별전환율")
    @GetMapping("/chart/gender/conversion")
    public Object getChartGenderConversion(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "성별 전환율 조회 실패";
        response.data = null;

        try {
            GraphDataGender data = graphService.getChartGenderConversion(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "그래프 조회 - 성별이탈률")
    @GetMapping("/chart/gender/bounce")
    public Object getChartGenderBounce(@RequestParam int test_no) {
        final RestResponse response = new RestResponse();

        response.status = false;
        response.msg = "성별 전환율 조회 실패";
        response.data = null;

        try {
            GraphDataGender data = graphService.getChartGenderBounce(test_no);
            response.status = true;
            response.msg = "success";
            response.data = data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
