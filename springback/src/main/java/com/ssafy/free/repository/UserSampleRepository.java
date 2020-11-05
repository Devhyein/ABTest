package com.ssafy.free.repository;

import java.time.LocalDate;
import java.util.Optional;

import com.ssafy.free.dto.UserSample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSampleRepository extends JpaRepository<UserSample, Integer> {

	UserSample findByEmail(String string);

	Optional<UserSample> findByEmailAndPw(String string, String string2);

	int countByTestNoAndPageTypeAndJoinDate(int test_no, String string, LocalDate start);

}
