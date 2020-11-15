package com.ssafy.free.repository.adminRepository;

import java.util.List;
import com.ssafy.free.dto.Admin.PageCnt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageCntRepository extends JpaRepository<PageCnt, Integer> {

    List<PageCnt> findByTestNoOrderByDateAsc(int testNo);

    List<PageCnt> findAllByTestNo(int testno);

}