package com.ssafy.free.repository.adminRepository;

import java.time.LocalDate;

import com.ssafy.free.dto.sample.Buyer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    int countByTestNoAndPageTypeAndDate(int testno, String string, LocalDate start);

}