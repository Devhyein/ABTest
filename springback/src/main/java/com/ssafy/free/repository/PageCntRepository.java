package com.ssafy.free.repository;

import java.time.LocalDate;
import java.util.List;
import com.ssafy.free.dto.PageCnt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageCntRepository extends JpaRepository<PageCnt, Integer> {

    List<PageCnt> findByTestNoOrderByDateAsc(int testNo);

}