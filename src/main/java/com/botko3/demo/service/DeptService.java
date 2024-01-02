package com.botko3.demo.service;

import com.botko3.demo.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    Dept getById(Integer id);
}
