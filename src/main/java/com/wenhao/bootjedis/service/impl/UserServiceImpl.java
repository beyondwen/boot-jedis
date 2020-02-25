package com.wenhao.bootjedis.service.impl;

import com.wenhao.bootjedis.domain.User;
import com.wenhao.bootjedis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Resource(name = "redisCacheTemplate")
    private HashOperations<String, String, User> hash;

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
    public void exprieString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, 100, TimeUnit.SECONDS);
        log.info("设置过期日期");
    }


    /**
     * 引用到很多同类型字符串如何解决？
     * -通过定义常量，或者枚举，或者domain中添加获取名称方法
     * <p>
     * redisCacheTemplate 操作需要写很长的方法名 如何解决
     * --通过查看 redisCacheTemplate.opsForHash() 得到返回值 再
     * --通过@Resource(name = "redisCacheTemplate") 注入 然后调用
     *
     * @param id id
     * @return User
     */
    @Override
    public User selectById(String id) {
        if (redisCacheTemplate.opsForHash().hasKey("user", id)) {
            log.info("从redis中查询");
            return hash.get("user", id);
            //return  (User)redisCacheTemplate.opsForHash().get("user", id);
        } else {
            User user = new User();
            user.setAge(22);
            user.setUsername("wenhao");
            user.setPassword("12345");
            //redisCacheTemplate.opsForHash().put("user", id, user);
            hash.put("user", id, user);
            log.info("从mysql查询");
            return user;
        }
    }
}
