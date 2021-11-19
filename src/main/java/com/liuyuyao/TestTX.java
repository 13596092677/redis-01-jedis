package com.liuyuyao;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Properties;

/**
 * @author ：lyy
 * @date ：Created in 2021/11/18 3:29 下午
 * @description：
 */
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("nae", "kuangshen");
//        jedis.watch("user1", "user2");
        jedis.set("user3", "str");
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
        try {
            multi.lset("mylist", 0, "1");
            multi.set("user1", "0");
            multi.set("user2", "1");
            multi.exec();
        } catch (Exception e) {
            System.out.println("go to catch block");
            multi.discard();
//            jedis.unwatch();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            System.out.println(jedis.get("user3"));
            jedis.close();
        }
    }
}
