/**
 * Created 2017-02-02 Copyright shashijie modified by <date> 2017-02-02 <user>shashijie
 * <description>
 */
package sy.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis缓存操作公共类
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-02-02
 * @since JDK 1.7
 */
public class RedisTemplateUtil {

    private final static Logger logger = LoggerFactory.getLogger(RedisTemplateUtil.class);

    /** shashijie 2017-02-03 Redis连接池 */
    private JedisPool jedisPool;

    /**
     * shashijie 2017-02-02 单个设置
     * @param key
     * @param value void
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.debug("缓存连接池异常:{}", e);
        } finally {
            close(jedis);
        }
    }

    /**
     * shashijie 2017-02-02 根据key获取value
     * @param key
     * @return String
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.debug("缓存连接池异常:{}", e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * shashijie 2017-02-03 回收连接池
     * @param jedis void
     */
    public void close(Jedis... jedis) {
        logger.debug("回收连接池");
        for (Jedis jed : jedis) {
            if (jed != null) {
                jed.close();
            }
        }
    }

    /**
     * shashijie 2017-02-03
     * @return the jedisPool
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * shashijie 2017-02-03
     * @param jedisPool the jedisPool to set
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public static void main(String[] args) {
        GenericObjectPoolConfig gc = new GenericObjectPoolConfig();
        gc.setMaxTotal(8);
        gc.setMaxIdle(6);
        gc.setMinIdle(5);
        JedisPool jp = new JedisPool(gc, "192.168.56.102",6379);
        Jedis jedis = jp.getResource();
        jedis.set("001", "hello word");
        System.out.println(jedis.get("001"));
        jedis.close();
        jp.close();
    }
    
}
