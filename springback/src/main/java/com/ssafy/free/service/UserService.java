package com.ssafy.free.service;

import java.util.HashMap;
import java.util.Optional;

import com.ssafy.free.dto.UserSample;

public interface UserService {

	int join(HashMap<String, Object> request);

	Optional<UserSample> login(HashMap<String, Object> request);

	int checkId(String id);

}
