package com.botko3.demo.service.impl;

import com.botko3.demo.entity.Dept;
import com.botko3.demo.entity.DeptLog;
import com.botko3.demo.mapper.DeptMapper;
import com.botko3.demo.mapper.EmpMapper;
import com.botko3.demo.service.DeptLogService;
import com.botko3.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;


    public List<Dept> list(){
        return deptMapper.list();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);
            //int i = 1/0;
            empMapper.deleteByDeptId(id);

        } finally {

            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setLog("Deleted department, the affected department is"+id+"Dept");
            deptLogService.insert(deptLog);

        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);


    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getByid(id);
    }


}
