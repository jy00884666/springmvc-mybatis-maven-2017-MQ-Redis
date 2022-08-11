/**
 * Created 2017-02-02
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-02-02 <user>shashijie <description>
 */
package sy.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import sy.controller.TextController;

import java.util.Arrays;

/**
 * Redis测试
 * @author shashijie
 * @description:
 * @reason: ADD REASON(可选)
 * @date 2017-02-02
 * @since JDK 1.7
 */
public class RedisTest {
    
    private final static Logger logger = LoggerFactory.getLogger(TextController.class);
    
    /**
     * shashijie 2017-02-02 ALM-
     * @param args void
     */
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.102", 6379);
            System.out.println(jedis.ping());
            
            // 添加
            jedis.set("hello", "word");
            
            System.out.println(jedis.get("hello"));
            System.out.println(jedis.get("001"));
            
        } catch (Exception e) {
            logger.info(Arrays.toString(e.getStackTrace()), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        
    }
    
}
