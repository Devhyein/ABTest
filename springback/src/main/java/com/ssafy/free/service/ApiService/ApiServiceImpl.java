package com.ssafy.free.service.ApiService;

import com.ssafy.free.component.Api.BucketComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService  {
    
    @Autowired
    BucketComponent bucketComponent;

    public void convert(String session_id){
        bucketComponent.getBucketNumBySessionId(session_id);
    }
}
