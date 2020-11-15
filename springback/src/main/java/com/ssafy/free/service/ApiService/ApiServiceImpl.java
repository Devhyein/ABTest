package com.ssafy.free.service.ApiService;

import com.ssafy.free.component.Api.BucketComponent;
import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Api.Context;
import com.ssafy.free.repository.ApiRepository.TestInfoRepository;
import com.ssafy.free.repository.adminRepository.TestRepository;

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
        log.info("Enter the API Convert");
        Context ret;
        // url을 기반으로 실험 정보를 가져온다.
        try {
            Test testInfo = testRepository.findByUrlAAndStatus(url, "진행중");
            ctx.setProgress(true);
            ctx.setTest_no(testInfo.getTestNo());

            // 비율에 따라 bucketNo을 분리
            ctx.setBucketNumber(bucketComponent.getBucketNumBySessionId(ctx.getSession_id(), 100));
            log.info("Get bucket No : " + ctx.getBucketNumber());

            ret = TestInfoRepository.getTestInfoByBucketNumber(ctx, testInfo);

            log.info("Test No : " + ctx.getTest_no());
            log.info("Session ID : " + ctx.getSession_id());
            log.info("Bucket Number : " + ctx.getBucketNumber());
            log.info("Test Variant : " + ctx.getPage_type());
            return ret;
        } catch (Exception e) {
            Test testInfo = testRepository.findByUrlA(url);
            ret = TestInfoRepository.getTestInfoByBucketNumber(ctx, testInfo);
            ret.setProgress(false);
            return ret;
        }
    }
}
