package com.ssafy.free.repository;

import com.ssafy.free.dto.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    
}
