package com.ssafy.free.repository;

import com.ssafy.free.dto.UserSample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSampleRepository extends JpaRepository<UserSample, Integer> {

	UserSample findByEmail(String string);
    
}
