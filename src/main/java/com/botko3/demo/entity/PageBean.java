package com.botko3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
pagination result class
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageBean {
    private Long total;
    private List rows;
}
