package com.ssafy.free.component.Api;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Api.Context;

import org.springframework.stereotype.Repository;

@Repository
public class TestInfoRepository {

    // TODO: 리팩토링(매직넘버, Stream API 구문 개선 등)
    public static Context getTestInfoByBucketNumber(Context ctx, Test testInfo) {

        int aNum = testInfo.getPerA();

        if (IntStream.rangeClosed(0, aNum - 1).boxed().collect(Collectors.toList()).contains(ctx.getBucketNumber())) {
            ctx.setPage_type("A");
        } else if (IntStream.rangeClosed(aNum, 99).boxed().collect(Collectors.toList())
                .contains(ctx.getBucketNumber())) {
            ctx.setPage_type("B");
        }

        return ctx;
    }

    private void updateTestInfo() {
        // TODO: 테스트 정보 업데이트
    }
}