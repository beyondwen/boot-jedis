package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String getString(String key) {
        Jedis jedis = jedisPool.getResource();
        String val;
        if (jedis.exists(key)) {
            log.info("从redis中查询到数据");
            val = jedis.get(key);
        } else {
            log.info("从MySql中查询到数据");
            val = "今天天气25°";
            jedis.set(key, val);
        }
        jedis.close();
        return val;
    }
}
