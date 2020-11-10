package com.ssafy.free.repository;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.free.dto.Admin.TestData;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface TestDataRepository extends JpaRepository<TestData, Integer> {

	public List<TestData> findAllByTestNoAndPageType(int no, String pageType);

	public int countByTestNoAndPageTypeAndDate(int test_no, String string, LocalDate date);

	public int countByTestNoAndPageTypeAndUrlNo(int testno, String string, int urlNo);

	public int countByTestNoAndPageType(int testNo, String string);

	public int countByTestNoAndPageTypeAndGender(int test_no, String string, String string2);

	public int countByTestNoAndPageTypeAndSigned(int test_no, String string, boolean b);

	public int countByTestNoAndPageTypeAndGenderAndUrlNo(int test_no, String string, String string2, Object urlno);

}
