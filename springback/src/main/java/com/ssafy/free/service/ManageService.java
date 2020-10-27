package com.ssafy.free.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.Test;

public interface ManageService {

	int createTest(HashMap<String, Object> request);
	int modifyTest(HashMap<String, Object> request) throws Exception;

	List<Test> getTestList(String email);

    
}
