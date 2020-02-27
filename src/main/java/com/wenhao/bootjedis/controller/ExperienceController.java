package com.wenhao.bootjedis.controller;

import com.wenhao.bootjedis.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
@Slf4j
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/")
    public void home(String id) {
        Future<Long> value = experienceService.controlService(id);
        try {
            Long l = value.get();
            if (l == 0L) {
                log.info("使用次数已经到达上限，请充值会员");
            } else if (Long.MAX_VALUE == l) {
                log.info("开始体验");
            } else {
                log.info("用户" + id + ":执行第" + ((10 - (Long.MAX_VALUE - l))) + "次体验中");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
