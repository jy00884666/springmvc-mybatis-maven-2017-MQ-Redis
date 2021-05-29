/**
 * Created 2017-02-02
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-02-02 <user>shashijie <description> 
 */
package sy.text;

import redis.clients.jedis.Jedis;

/**Redis测试
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-02-02
 * @since JDK 1.7
 */
public class RedisTest {

    /**shashijie 2017-02-02 ALM-
     * @param args void
     */
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.102",6379);
            System.out.println(jedis.ping());
            
            // 添加
            jedis.set("hello", "word");
            
            System.out.println(jedis.get("hello"));
            System.out.println(jedis.get("001"));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

}
