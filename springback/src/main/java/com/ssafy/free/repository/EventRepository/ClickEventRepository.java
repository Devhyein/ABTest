package com.ssafy.free.repository.EventRepository;

import com.ssafy.free.dto.ClickEvent.ClickData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickEventRepository extends JpaRepository<ClickData, Integer> {

}
