package com.botko3.demo.controller;

import com.botko3.demo.entity.Dept;
import com.botko3.demo.entity.Result;
import com.botko3.demo.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    private  static Logger log = LoggerFactory.getLogger(DeptController.class);

    /*
    Show all depts info
     */
    @GetMapping
    public Result list(){
        //log.info("All depts info");
        List<Dept> deptList =deptService.list();
        return Result.success(deptList);

    }

    /*
    Delete dept by id
     */

    @DeleteMapping("/{id}")
    public Result deletebyId(@PathVariable Integer id){
        //log.info("Delete by id:",id);
        deptService.delete(id);
        return Result.success();

    }
    /*
    add new dept
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);

    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        deptService.add(dept);
        return Result.success();
        

    }
    /*
    get by id
     */

    /*
    update dept
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success();
    }


}
