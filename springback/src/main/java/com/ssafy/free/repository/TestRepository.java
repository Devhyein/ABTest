package com.ssafy.free.repository;

import java.util.List;

import com.ssafy.free.dto.Test;
import com.ssafy.free.dto.TestResponse;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface TestRepository extends JpaRepository<Test, Integer> {

	List<TestResponse> findAllByAdminNo(int adminno);

	List<TestResponse> findAllByAdminNoAndStatus(int admin_no, String status);

	Test findByAdminNoAndTestTitle(int admin_no, String testTitle);

}
