package com.ssafy.free.repository;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.free.dto.TestData;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface TestDataRepository extends JpaRepository<TestData, Integer> {

	List<TestData> findAllByTestNoAndPageType(int no, String pageType);

	int countByTestNoAndPageTypeAndDate(int test_no, String string, LocalDate date);

}
