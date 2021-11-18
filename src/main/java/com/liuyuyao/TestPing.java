package com.liuyuyao;

import redis.clients.jedis.Jedis;

/**
 * @author ：lyy
 * @date ：Created in 2021/11/18 11:21 上午
 * @description：
 */
public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.keys("*"));
    }
}
