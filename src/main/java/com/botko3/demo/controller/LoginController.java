package com.botko3.demo.controller;

import com.botko3.demo.entity.Emp;
import com.botko3.demo.entity.Result;
import com.botko3.demo.service.EmpService;
import com.botko3.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/login")
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp){
        //log.info("員工登陸：{}",emp);
        Emp emp1 = empService.login(emp);
        //return emp1 != null ? Result.success() : Result.error("Invalid Username or Password");

        /*
        Login successful:generate jwt and dispatch jwt
         */

        if(emp1 != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",emp1.getId());
            claims.put("name",emp1.getName());
            claims.put("username",emp1.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return Result.success(jwt);

        }

        return Result.error("Invalid Username or Password");

        /*
        login failed:return error
         */
    }
}
