package com.ssafy.free.repository;

import java.util.List;

import com.ssafy.free.dto.Admin.*;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface TestRepository extends JpaRepository<Test, Integer> {

	List<TestResponse> findAllByAdminNoOrderByTestNoDesc(int adminno);

	List<TestResponse> findAllByAdminNoAndStatusOrderByTestNoDesc(int adminNo, String status);

	Test findByAdminNoAndTestTitle(int adminNo, String testTitle);

	int countByUrlAAndStatus(String urlA, String string);

	int countByUrlBAndStatus(String urlB, String string);

	Test findByUrlAAndStatus(String urlA, String Status);

	Test findByUrlA(String urlA);

	Test findByUrlBAndStatus(String urlB, String Status);

}
