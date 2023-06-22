package com.pat.jp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 14:16
 **/
@SpringBootTest
public class RedisTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void redisTest() {
        // opsForValue 操作字符串，类似String
        // opsForList 操作list，类似list
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog

        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，和基本的CRUD

        // 获取连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("mykey", "hello");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

}
