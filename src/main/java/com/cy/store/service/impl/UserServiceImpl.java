package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-20  20:42
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public Integer changePassword(String oldPassword, String newPassword, String username) {
        User user = userMapper.findByUsername(username);
        //这里要看 user得到的密码是 MD5 前的还是 MD5 后的
        String oldSQLPassword = user.getPassword();
        if(oldSQLPassword.equals(oldPassword)){
            user.setPassword(newPassword);
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newMD5Password=getMD5Password(user.getPassword(),salt);
        //鉴别一下 是否更新成功
        if(userMapper.findByUsername(username).getPassword().equals(newMD5Password)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public User login(String username,String password){
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User byUsername = userMapper.findByUsername(username);

        // 判断查询结果是否为null
        // 是：抛出UserNotFoundException异常
        if(byUsername==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：抛出UserNotFoundException异常
        if(byUsername.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        // 从查询结果中获取盐值
        String salt = byUsername.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = getMD5Password(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        // 是：抛出PasswordNotMatchException异常
        if(!byUsername.getPassword().equals(md5Password)){
            throw new PasswordNotMatchException("密码不正确");
        }
        // 创建新的User对象
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        // 返回新的user对象
        User user = new User();
        user.setUsername(byUsername.getUsername());
        user.setPassword(byUsername.getPassword());
        user.setAvatar(byUsername.getAvatar());
        return user;
    }


    @Override
    public void register(User user) {
        //根据参数获得用户信息，
        // 返回数据库查询是否存在，mapper层 findByUsername方法
        // 如果已经存在，返回UsernameDuplicateException
        String username = user.getUsername();
        User byUsername = userMapper.findByUsername(username);
        if(byUsername !=null){
            throw new UsernameDuplicateException(username+"已注册,请重新注册");
        }

        // 创建当前时间对象
        // 补全数据：加密后的密码
        // 补全数据：盐值
        // 补全数据：isDelete(0)
        // 补全数据：4项日志属性
        Date now = new Date();

        String salt = UUID.randomUUID().toString().toUpperCase();
        String MD5Password=getMD5Password(user.getPassword(),salt);
        user.setPassword(MD5Password);
        user.setSalt(salt);

        user.setIsDelete(0);

        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);

        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        // 判断受影响的行数是否不为1
        // 是：插入数据时出现某种错误，则抛出InsertException异常

        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    //MD5加密过程
    private String getMD5Password(String password,String salt){
        String MD5Password = DigestUtils.md5DigestAsHex((salt + password + salt).
                getBytes()).toUpperCase();
        return MD5Password;
    }

    //TODO
//    MD5解密过程

}
