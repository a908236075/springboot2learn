package com.xiaobo.springboot2.redis;

import com.xiaobo.springboot2.Springboot2learnApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot2learnApplication.class)
class RedisTemplateControllerTest {

    @Autowired
   private RedisTemplate<String,String> redisTemplate;

    @Test
    public void helloWorld(){
        redisTemplate.opsForValue().set("redisLearn","hello World redis");
        System.out.println(redisTemplate.opsForValue().get("redisLearn"));
    }
    @Test
    public void stringExpireTest(){
        redisTemplate.expire("helloKey",100, TimeUnit.SECONDS);
        redisTemplate.expireAt("helloKey", new Date());
        Long leftExpireTime = redisTemplate.getExpire("helloKey");
        System.out.println(leftExpireTime);
    }

    /**
     * 以集合的方式,一次存入多个key
     */
    @Test
    public void stringsRedisTest(){

        HashMap<String, String> setRedis = new HashMap<>();
        setRedis.put("setRedis1","1");
        setRedis.put("setRedis2","2");
        setRedis.put("setRedis3","3");
        redisTemplate.opsForValue().multiSet(setRedis);
        BoundValueOperations<String, String> setRedis1 = redisTemplate.boundValueOps("setRedis1");
        String setRedis11 = redisTemplate.opsForValue().get("setRedis1");

    }
    @Test
    public void hashRedisTest(){
//        redisTemplate.opsForHash().put("hashTest","key1","value1");
//        redisTemplate.opsForHash().put("hashTest","key2","value2");
//        redisTemplate.opsForHash().put("hashTest","key3","value3");
//        redisTemplate.opsForHash().put("hashTest","key4","value4");
        /*Object o = redisTemplate.opsForHash().get("hashTest", "key1");
        System.out.println(o);*/
        BoundHashOperations<String, Object, Object> hashTest = redisTemplate.boundHashOps("hashTest");
        System.out.println(hashTest.entries());

    }
    @Test
    public void listRedisTest(){
        redisTemplate.opsForList().leftPush("listKey1","listValue1");

    }

    @Test
    public void setRedisTemplate(){
        redisTemplate.opsForSet().add("setTest","1","3","3","6");



    }








}
