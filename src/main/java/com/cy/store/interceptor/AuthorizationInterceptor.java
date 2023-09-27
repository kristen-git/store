package com.cy.store.interceptor;

import com.cy.store.entity.User;
import com.cy.store.util.JwtUtils;
import com.cy.store.util.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zhuang Xin
 * @CreateTime: 2023-08-15  10:50
 * @Description: 过滤规则
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //验证前端是否将 token发送给后端
        //首先验证header
        String token = request.getHeader(jwtUtils.getHeader());
        ResultJson<Object> objectResultJson = new ResultJson<>();
        try {
            //header中若没有，验证parameter
            if(StringUtils.isBlank(token)) {
                token = request.getParameter(jwtUtils.getHeader());
            }
        } catch (Exception e) {
            throw new RuntimeException(jwtUtils.getHeader() + "token不能为空");
        }
        //验证redis中是否有这个token
        //并重新刷新token生效时长，例如增加一半的时间
        //TODO
        if(redisTemplate.opsForValue().get(token)==null){
            response.setContentType("application/json");
            response.getWriter().write(200);
            return false;
        }else {
            redisTemplate.expire(token,30,TimeUnit.MINUTES);
            return true;
        }
    }
}
