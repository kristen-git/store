package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-15  17:58
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

}
