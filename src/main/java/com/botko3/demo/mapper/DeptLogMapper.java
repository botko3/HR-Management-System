package com.botko3.demo.mapper;

import com.botko3.demo.entity.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(createTime,log) values(#{createTime},#{log})")
    void insert(DeptLog deptLog);
}
