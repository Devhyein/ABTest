package com.ssafy.free.service;

import java.util.HashMap;

import com.ssafy.free.dto.UserSample;
import com.ssafy.free.repository.UserRepository;
import com.ssafy.free.repository.UserSampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserSampleRepository userRepository;

    @Override
    public int join(HashMap<String, Object> request) {
        UserSample user = new UserSample(request.get("id").toString(), request.get("pw").toString(), 
            Integer.parseInt(request.get("age").toString()), request.get("gender").toString());
        try {
            userRepository.save(user);
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
