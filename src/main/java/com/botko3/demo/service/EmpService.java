package com.botko3.demo.service;

import com.botko3.demo.entity.Emp;
import com.botko3.demo.entity.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    public Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
