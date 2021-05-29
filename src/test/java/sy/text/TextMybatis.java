/**
 * Created 2017-01-15
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-01-15 <user>shashijie <description> 
 */
package sy.text;


import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sy.model.Text;
import sy.service.TextService;

/*继承类注解*/
@RunWith(SpringJUnit4ClassRunner.class)
/*引入配置文件*/
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TextMybatis {

    private final static Logger logger = LoggerFactory.getLogger(TextMybatis.class);
    
    @Autowired
    private TextService textService;

    @Test
    public void test1(){
        logger.debug("开始执行测试test1");
        Text text = textService.getTextById(new BigDecimal("0"));
        if (text == null) {
            System.out.println("无数据!");
        } else {
            System.out.println(text.getKmmc());
        }
        logger.debug("测试方法结束{}",text);
    }
    
    /**shashijie 2017-01-15
     * @return the textService
     */
    public TextService getTextService() {
        return textService;
    }

    /**shashijie 2017-01-15
     * @param textService the textService to set
     */
    public void setTextService(TextService textService) {
        this.textService = textService;
    }
    
    
    
}
