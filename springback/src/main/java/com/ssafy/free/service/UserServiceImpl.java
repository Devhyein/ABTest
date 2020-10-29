package com.ssafy.free.service;

import java.util.HashMap;
import java.util.Optional;

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
        // 이미 있는 아이디인지 먼저 확인
        UserSample exist =userRepository.findByEmail(request.get("id").toString());
        if(exist!=null){
            return -1;
        }

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

    @Override
    public String login(HashMap<String, Object> request) {
        // id pw 일치하는 UserSample이 있는지 확인
        try {
            Optional<UserSample> user = userRepository.findByEmailAndPw(request.get("id").toString(), request.get("pw").toString());
            if(user.isPresent()){
                return user.get().getEmail();
            } else {
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
