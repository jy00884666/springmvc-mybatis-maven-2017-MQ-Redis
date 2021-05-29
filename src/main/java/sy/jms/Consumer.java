/**
 * Created 2017-01-20 Copyright shashijie modified by <date> 2017-01-20 <user>shashijie
 * <description>
 */
package sy.jms;

import com.alibaba.fastjson.JSON;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * 消息的消费者（接受者）
 * @description: SessionAwareMessageListener是Spring为我们提供的，它不是标准的JMS
 *               MessageListener。MessageListener的设计只是纯粹用来接收消息的
 *               ，假如我们在使用MessageListener处理接收到的消息时我们需要发送一个消息通知对方我们已经收到这个消息了
 *               ，那么这个时候我们就需要在代码里面去重新获取一个Connection或Session
 *               。SessionAwareMessageListener的设计就是为了方便我们在接收到消息后发送一个回复的消息
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-20
 * @since JDK 1.7
 */
public class Consumer implements SessionAwareMessageListener<ObjectMessage> {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    /** shashijie 2017-01-20 消息发送类 */
    private Producer producer;//若处理失败则可以继续存入消息队列

    public void onMessage(ObjectMessage message, Session session) throws JMSException {
        // 消息是否已经投递过
        try {
            if (message.getJMSRedelivered()) {
                logger.warn("...(" + message.getJMSMessageID() + ") is redelivered");
            }
        } catch (Exception e) {
            logger.error("信号接收监听异常{}", e);
            logger.error(e.getMessage());
            return;
        }

        // 对象转化:接受Object类型消息
        ObjectMessage om = (ObjectMessage) message;
        SignalInfo vo = null;

        try {
            vo = (SignalInfo) om.getObject();
        } catch (Exception e) {
            logger.error("[" + message.getJMSDestination() + "]:[ " + message.getJMSMessageID() + "]-信号转换异常",
                    e);
            return;
        }

        logger.debug("SignalInfo:{}", JSON.toJSONString(vo));

        logger.info("[" + message.getJMSDestination() + "]: " + message.getJMSMessageID() + "]-信号接收成功");

    }

    /**
     * shashijie 2017-01-20
     * @return the producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * shashijie 2017-01-20
     * @param producer the producer to set
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

}
