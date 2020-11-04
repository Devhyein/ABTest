package com.ssafy.free.service;

import java.util.HashMap;

public interface UserService {

	int join(HashMap<String, Object> request);

	String login(HashMap<String, Object> request);

	int checkId(String id);
    
}
