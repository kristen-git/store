package com.cy.store;

import com.cy.store.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Author: Zhuang Xin
 * @CreateTime: 2023-07-25  10:41
 * @Description: TODO
 */
@SpringBootTest
public class JWTTest {
    @Test
    public void JwtTest(){
        Date nowDate = new Date();
        JwtUtils jwtUtils = new JwtUtils();
        jwtUtils.setExpire(10);
        jwtUtils.setSecret("f4e2e52034348f86b67cde581c0f9eb5");
        jwtUtils.setHeader("");
        String s = jwtUtils.generateToken(123456);
        String header = jwtUtils.getHeader();
        String secret = jwtUtils.getSecret();
        long expire = jwtUtils.getExpire();
        String token = jwtUtils.generateToken(123456);
        Claims claimByToken = jwtUtils.getClaimByToken(token);
        System.out.println(s);
        System.out.println(header);
        System.out.println(secret);
        System.out.println(expire);
        System.out.println(token);
        System.out.println(claimByToken);

    }
}
