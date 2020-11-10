package com.ssafy.free.service;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

import com.ssafy.free.dto.sample.UserSample;
import com.ssafy.free.repository.UserSampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserSampleRepository userRepository;

    @Override
    public int join(HashMap<String, Object> request) {

        LocalDate date = LocalDate.parse(request.get("join_date").toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate birth = LocalDate.parse(request.get("birth").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int year = Year.now().getValue();

        int age = year - birth.getYear();

        age = age / 10 * 10;

        UserSample user = new UserSample(Integer.parseInt(request.get("test_no").toString()),
                request.get("id").toString(), request.get("pw").toString(), birth, age,
                request.get("gender").toString(), request.get("page_type").toString(), date,
                request.get("session_id").toString());
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<UserSample> login(HashMap<String, Object> request) {
        // id pw 일치하는 UserSample이 있는지 확인
        try {
            Optional<UserSample> user = userRepository.findByEmailAndPw(request.get("id").toString(),
                    request.get("pw").toString());
            if (user.isPresent()) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int checkId(String id) {
        UserSample exist = userRepository.findByEmail(id);
        if (exist != null) {
            return -1;
        }

        return 1;
    }

}
