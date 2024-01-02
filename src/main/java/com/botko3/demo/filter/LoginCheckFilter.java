package com.botko3.demo.filter;

import com.botko3.demo.entity.Result;
import com.botko3.demo.utils.JwtUtils;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    Gson gson = new Gson();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;

        //get request url
        String requestUrl = request.getRequestURL().toString();
        //check if url contains login
        //if its login operation, continues
        if(requestUrl.contains("login")){
            log.info("Its Login Page");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //get request header token
        String jwt = request.getHeader("token");

        //check if token exist, if no ,return error
        if(!StringUtils.hasLength(jwt)){
            log.info("Request header token is empty. Return NOT_LOGIN");
            Result error =  Result.error("NOT_LOGIN");
            String notLogin = gson.toJson(error);
            response.getWriter().write(notLogin);
            return;

        }

        //parse token, if failed, return error
        try {
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            log.info("invalid jwt,return NOT_LOGIN");
            Result error =  Result.error("NOT_LOGIN");
            String notLogin = gson.toJson(error);
            response.getWriter().write(notLogin);
            return;
        }

        //continues
        log.info("Valid jwt,continues");
        filterChain.doFilter(servletRequest,servletResponse);

    }

}
