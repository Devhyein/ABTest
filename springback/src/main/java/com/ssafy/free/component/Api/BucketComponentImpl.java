package com.ssafy.free.component.Api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BucketComponentImpl implements BucketComponent {

    private static int BUCKET_SIZE = 100;

    @Override
    public int getBucketNumBySessionId(String session_id) {

        // 해당 샘플에서, 버킷 번호를 구하는 방법이 중요하지는 않음
        // 0 ~ 99 의 범위 반환, 동일한 id 인 경우 항상 일관 된 버킷 번호를 반환해 줌
        //return Math.abs(uuid.hashCode()) % 100;

        return Hashing.consistentHash(
                Hashing.sha256().hashString(session_id, Charsets.UTF_8), BUCKET_SIZE
        );
    }
}
