package com.ssafy.free.service.AdminService;

import java.util.HashMap;

import com.ssafy.free.dto.Admin.TestResponse;

import org.springframework.data.domain.Page;

public interface ManageService {

	int createTest(HashMap<String, Object> request);

	int modifyTest(HashMap<String, Object> request) throws Exception;

	Page<TestResponse> getTestList(String email, int page);

	Page<TestResponse> getTestListBefore(String email, int page);

	Page<TestResponse> getTestListProgress(String email, int page);

	Page<TestResponse> getTestListComplete(String email, int page);

	int deleteTest(int testNo);

	String login(HashMap<String, Object> request);

}
