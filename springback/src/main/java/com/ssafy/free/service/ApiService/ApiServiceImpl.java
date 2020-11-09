package com.ssafy.free.service.ApiService;

import java.util.List;

import com.ssafy.free.component.Api.BucketComponent;
import com.ssafy.free.component.Api.TestInfoRepository;
import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Api.Context;
import com.ssafy.free.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    BucketComponent bucketComponent;
    @Autowired
    TestRepository testRepository;

    public Context convert(Context ctx, String url) {

        // url을 기반으로 실험 정보를 가져온다.
        try {
            Test testInfo = testRepository.findByurlAAndStatus(url, "진행중");
            ctx.setTest_no(testInfo.getTestNo());
            log.info("Get test No : " + ctx.getTest_no());

            // 비율에 따라 bucketNo을 분리
            ctx.setBucketNumber(bucketComponent.getBucketNumBySessionId(ctx.getSession_id(), 100));
            log.info("Get bucket No : " + ctx.getBucketNumber());

            Context ret = TestInfoRepository.getTestInfoByBucketNumber(ctx, testInfo);

            log.info("Test No : " + ctx.getTest_no());
            log.info("Session ID : " + ctx.getSession_id());
            log.info("Bucket Number : " + ctx.getBucketNumber());
            log.info("Test Variant : " + ctx.getPage_type());
            return ret;
        } catch (Exception e) {
            Context ret = null;
            return ret;
        }
    }
}
