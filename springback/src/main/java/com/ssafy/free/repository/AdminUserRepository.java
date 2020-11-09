package com.ssafy.free.repository;

import java.util.Optional;

import com.ssafy.free.dto.Admin.AdminUser;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

	AdminUser findOneByEmail(String email);

	Optional<AdminUser> findByEmailAndPw(String string, String string2);

}
