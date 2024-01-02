package com.botko3.demo.interceptor;

import com.botko3.demo.entity.Result;
import com.botko3.demo.utils.JwtUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    Gson gson = new Gson();

    //目標資源運行前放行。返回true，放行。反之不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //get request url
        String requestUrl = request.getRequestURL().toString();
        //check if url contains login
        //if its login operation, continues
        if(requestUrl.contains("login")){
            log.info("Its Login Page");
            return true;
        }

        //get request header token
        String jwt = request.getHeader("token");

        //check if token exist, if no ,return error
        if(!StringUtils.hasLength(jwt)){
            log.info("Request header token is empty. Return NOT_LOGIN");
            Result error =  Result.error("NOT_LOGIN");
            String notLogin = gson.toJson(error);
            response.getWriter().write(notLogin);
            return false;

        }

        //parse token, if failed, return error
        try {
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            log.info("invalid jwt,return NOT_LOGIN");
            Result error =  Result.error("NOT_LOGIN");
            String notLogin = gson.toJson(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //continues
        log.info("Valid jwt,continues");
        return true;
    }


    @Override//目標資源運行後運行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle");
    }

   @Override//視圖渲染後放行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
       System.out.println("afterCompletion");
    }
}
