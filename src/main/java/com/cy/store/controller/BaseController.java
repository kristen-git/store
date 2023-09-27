package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-21  21:35
 * @Description: TODO
 */

public class BaseController {
    public static final int OK=200;
    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中
    //这个方法此时就冲当得是请求处理方法，处理结果直接返回给前端
    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler(ServiceException.class)
    public ResultJson<Void> handlerException(Throwable e){
        ResultJson<Void> result=new ResultJson<Void>(e);
        if(e instanceof UsernameDuplicateException){
            result.setCode(400);
        }else if(e instanceof InsertException){
            result.setCode(500);
        }else if(e instanceof PasswordNotMatchException){
            result.setCode(600);
        }else if(e instanceof UserNotFoundException){
            result.setCode(700);
        }
        return result;
    }

}
