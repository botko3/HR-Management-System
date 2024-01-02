package com.botko3.demo.filter;

import jakarta.servlet.*;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override//init method, execute once only
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("初始化了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("攔截了");
        System.out.println("執行放行前邏輯");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        //回到filter，執行放行後邏輯

        System.out.println("執行放行後邏輯");



    }

    @Override// destory method, execute once only
    public void destroy() {
        Filter.super.destroy();
    }
}
