package com.ssafy.free.repository.adminRepository;

import java.util.List;

import com.ssafy.free.dto.Admin.UrlAttribute;

import org.springframework.data.jpa.repository.JpaRepository;

// 필요한 repository 있으면 이렇게 interface로 만들어서 추가하기. 기본 CRUD는 이미 구현돼있어여
public interface UrlAttributeRepository extends JpaRepository<UrlAttribute, Integer> {

    List<UrlAttribute> findByTestNo(int testno);

    UrlAttribute findByTestNoAndUrlName(int testno, String url);

    List<String> findUrlNameByTestNo(int test_no);

    UrlAttribute findByUrlNameAndTestNo(String urlName, int test_no);

}