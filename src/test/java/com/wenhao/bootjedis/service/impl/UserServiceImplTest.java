package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getString() {
        String wenhao = userService.getString("wenhao");
        log.info(wenhao);
    }
}