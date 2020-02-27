package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.domain.User;
import com.wenhao.bootjedis.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Resource(name = "redisCacheTemplate")
    private HashOperations<String, String, User> hash;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> string;

    @Override
    @Async
    public Future<Long> controlService(String id) {
        try {
            if (stringRedisTemplate.hasKey("compid:" + id)) {
                Long increment = string.increment("compid:" + id);
                return new AsyncResult<>(increment);
            } else {
                string.set("compid:" + id, Long.MAX_VALUE - 10 + "");
                return new AsyncResult<>(Long.MAX_VALUE - 10);
            }
        } catch (RedisSystemException e) {
            return new AsyncResult<>(0L);
        }
    }

    @Override
    public void businessExperience(Long increment) {
        log.info("执行第" + (Long.MAX_VALUE - increment) + "次体验中");

    }


}

/*class MyThread extends Thread {
    ExperienceServiceImpl experienceService = new ExperienceServiceImpl();

    @Override
    public void run() {
        while (true) {
            experienceService.startThread("初级用户");
        }
    }
}*/

/*class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}*/
