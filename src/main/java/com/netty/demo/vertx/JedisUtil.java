package com.netty.demo.vertx;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.*;

@Slf4j
public final class JedisUtil {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //连接池中最大对象数量
        jedisPoolConfig.setMaxTotal(100);
        //最大能够保持idel状态的对象数
        jedisPoolConfig.setMaxIdle(100);
        //最小能够保持idel状态的对象数
        jedisPoolConfig.setMinIdle(30);
        //当池内没有可用资源,最大等待时长
        jedisPoolConfig.setMaxWaitMillis(3000);
        //在对象Idel状态下是否会调用validateObject进行检查
        jedisPoolConfig.setTestWhileIdle(false);
        //evict线程对object进行扫描的时间间隔
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(3000);
        //表示对象的空闲时间，如果超过这个时间对象没有被使用则变为idel状态
        //然后才能被evict线程扫描并驱逐；
        //这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        //-1 表示对象不会变成idel状态
        jedisPoolConfig.setMinEvictableIdleTimeMillis(10000);
        //表示idle object evitor每次扫描的最多的对象数；
        jedisPoolConfig.setNumTestsPerEvictionRun(10);
        //在从池中获取对象时调用validateObject方法检查
        jedisPoolConfig.setTestOnBorrow(false);
        //在把对象放回池中时调用validateObject方法检查
        jedisPoolConfig.setTestOnReturn(false);

        pool = new JedisPool(jedisPoolConfig, "192.168.80.112");
    }

    public static void setString(String key, String value, long expireAt) {
        try(Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
            jedis.expireAt(key, expireAt + System.currentTimeMillis());
        } catch (Exception e) {
            log.error("redis error", e);
        }
    }

    public static String getString(String key) {
        try(Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("redis error", e);
        }
        return null;
    }

    public static void delString(String key) {
        try(Jedis jedis = pool.getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            log.error("redis error", e);
        }
    }

    public static void incr(String key) {
        try(Jedis jedis = pool.getResource()) {
            jedis.incr(key);
        } catch (Exception e) {
            log.error("redis error", e);
        }
    }

}
