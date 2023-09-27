package com.cy.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Zhuang Xin
 * @CreateTime: 2023-02-21  21:20
 * @Description: TODO
 */
@Data
public class ResultJson<E> implements Serializable {
    private Integer code;
    private String message;
    private E data;
    public ResultJson(){
        super();
    }
    public ResultJson(Integer code){
        super();
        this.code=code;
    }
    //出现异常调用
    public ResultJson(Throwable e){
        super();
        this.message= e.getMessage();
    }
    public ResultJson(Integer code,E data){
        super();
        this.code=code;
        this.data=data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
