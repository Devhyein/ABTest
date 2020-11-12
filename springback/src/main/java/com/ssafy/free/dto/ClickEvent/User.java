package com.ssafy.free.dto.ClickEvent;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
    private String email;
    private String gender;
    private int age;
    private LocalDate join_date;

    public User() {
    }

    public User(String email, String gender, int age, LocalDate join_date) {
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.join_date = join_date;
    }

}
