package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ExperienceServiceImplTest {

    @Autowired
    private ExperienceService experienceService;

    @Test
    void businessExperience() {
        /*while (true) {
            int x = experienceService.controlService("level");
            if (x == 0){
                break;
            }
        }*/

    }
}