package com.botko3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private  Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private  String image;

    private Short job;
    private LocalDate entrydate;

    private Short deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
