package com.ssafy.free.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_no")
    private int test_no; 

    @Column(name = "test_title")
    private String test_title; 

    @Column(name = "test_a")
    private String test_a; 

    @Column(name = "test_b")
    private String test_b; 

    @Column(name = "start")
    private LocalDateTime start; 

    @Column(name = "end")
    private LocalDateTime end; 

    @Column(name = "gender")
    private int gender; 

    @Column(name = "age")
    private int age; 

    @Column(name = "per")
    private int per; 

    @Column(name = "status")
    private int status; 

    
}
