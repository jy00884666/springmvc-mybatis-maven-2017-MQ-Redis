/**
 * Created 2017-01-20 Copyright shashijie modified by <date> 2017-01-20 <user>shashijie
 * <description>
 */
package sy.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import sy.utils.StringUtil;

/**
 * ActiveMQ消息的生产者（发送者）
 * @description: 测试使用前需要开启ActiveMQ服务
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-20
 * @since JDK 1.7
 */
public class Producer {

    private static final Logger logger = Logger.getLogger(Producer.class);

    /**shashijie 2017-01-20 Spring支持jms消息对象*/
    private JmsTemplate jmsTemplate;

    /**shashijie 2017-01-20 消息队列key*/
    private String destinationName;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * 根据指定目的地发送消息
     * @throws RuntimeException
     */
    public void send(String destinationName, final SignalInfo signalInfo) throws RuntimeException {

        // 验证信号
        validateSignalInfo(signalInfo);

        try {
            this.jmsTemplate.send(destinationName, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    //发送Object类型消息,发送对象时必须让该对象实现serializable接口  
                    return session.createObjectMessage(signalInfo);
                }
            });
            logger.info("push message in Queue[" + destinationName + "]. message= " + signalInfo);

        } catch (Exception e) {
            throw new RuntimeException("信号发送异常。详细原因如下：", e);
        }
    }

    /**
     * 根据配置的默认目的地发送消息
     * @throws RuntimeException
     */
    public void send(final SignalInfo signalInfo) throws RuntimeException {

        if (destinationName == null) {
            throw new RuntimeException("没有配置默认目的队列");
        }

        // 验证信号
        validateSignalInfo(signalInfo);

        try {
            this.jmsTemplate.send(destinationName, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(signalInfo);
                }
            });
            logger.info("push message in Queue[" + destinationName + "]. message= " + signalInfo);

        } catch (Exception e) {
            throw new RuntimeException("信号发送异常。详细原因如下：", e);
        }
    }

    /**
     * 验证信息类是否正确
     * @throws RuntimeException
     */
    public void validateSignalInfo(SignalInfo signalInfo) throws RuntimeException {

        // 如有需要后续补充

        /** 应用码 */
        if (StringUtil.isEmpty(signalInfo.getAppCode())) {
            throw new RuntimeException("AppCode应用码 不能为空。");
        }
        /** 交易码 */
        /*if (StringUtil.isEmpty(signalInfo.getTxCode())) {
            throw new RuntimeException("TxCode交易码 不能为空。");
        }
        *//** 交易日期 *//*
        if (StringUtil.isEmpty(signalInfo.getTxDt())) {
            throw new RuntimeException("TxDt交易日期 不能为空。");
        }
        *//** 交易时间 *//*
        if (StringUtil.isEmpty(signalInfo.getTxTm())) {
            throw new RuntimeException("TxTm交易时间 不能为空。");
        }
        *//** 客户号 *//*
        if (StringUtil.isEmpty(signalInfo.getCustNo())) {
            throw new RuntimeException("CustNo客户号 不能为空。");
        }
        *//** 分销机构代码 *//*
        if (StringUtil.isEmpty(signalInfo.getDisCode())) {
            throw new RuntimeException("DisCode分销机构代码 不能为空。");
        }
        *//** 网点代码 *//*
        if (StringUtil.isEmpty(signalInfo.getOutletCode())) {
            throw new RuntimeException("OutletCode网点代码 不能为空。");
        }
        *//** 交易渠道 *//*
        if (StringUtil.isEmpty(signalInfo.getTradeChan())) {
            throw new RuntimeException("TradeChan交易渠道 不能为空。");
        }
        *//** 流水号 *//*
        if (StringUtil.isEmpty(signalInfo.getSerialNo())) {
            throw new RuntimeException("SerialNo流水号 不能为空。");
        }*/

    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
