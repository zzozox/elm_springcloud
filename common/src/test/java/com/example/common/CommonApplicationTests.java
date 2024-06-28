package com.example.common;

import com.example.common.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

@SpringBootTest
class CommonApplicationTests {

    @Resource
    JwtUtils jwtUtils;
    @Test
    void contextLoads() {
        String jwt = jwtUtils.createJwt(new User("name","123",null),"name",2);
        System.out.println(jwt);
    }

}
