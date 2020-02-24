package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.domain.User;
import com.wenhao.bootjedis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Override
    public String getString(String key) {
        String val = "";
        if (redisCacheTemplate.hasKey(key)) {
            log.info("从redis中查询到数据");
            return stringRedisTemplate.opsForValue().get(key);
        } else {
            val = "今天天气25°";
            log.info("从MySql中查询到数据");
            stringRedisTemplate.opsForValue().set(key, val);
            return val;
        }
    }

    @Override
    public void delString(String key) {
        if (redisCacheTemplate.hasKey(key)) {
            log.info("从redis中查询到数据");
            stringRedisTemplate.delete(key);
        }
    }

    @Override
    public void exprieString(String key,String value) {
        stringRedisTemplate.opsForValue().set(key,value);
        stringRedisTemplate.expire(key,100, TimeUnit.SECONDS);
        log.info("设置过期日期");
    }

    @Override
    public User setHash(String key) {
        User user = new User();
        user.setAge(22);
        user.setUsername("wenhao");
        user.setPassword("12345");
        if (stringRedisTemplate.opsForHash().hasKey("user",user)){
            Object user1 = stringRedisTemplate.opsForHash().get("user", user);
            log.info("从redis中查询");
            return null;
        }else {
            stringRedisTemplate.opsForHash().put("user", key, user);
            log.info("从redis中查询");
            return user;
        }
    }
}
