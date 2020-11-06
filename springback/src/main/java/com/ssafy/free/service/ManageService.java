package com.ssafy.free.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.TestResponse;

public interface ManageService {

	int createTest(HashMap<String, Object> request);
	int modifyTest(HashMap<String, Object> request) throws Exception;

	List<TestResponse> getTestList(String email);
	List<TestResponse> getTestListBefore(String email);
	List<TestResponse> getTestListProgress(String email);
	List<TestResponse> getTestListComplete(String email);
	int deleteTest(int test_no);
	String login(HashMap<String, Object> request);

    
}
