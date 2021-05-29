package sy.ext;

import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 外部配置文件加载类
 */
public class ExtPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ExtPropertyPlaceholderConfigurer.class);
    
    /**shashijie 2017-02-04 同步父类localProperties属性*/
    private static Properties[] properties;
    
    /**
     * shashijie 2017-01-16 初始化加载配置文件
     * @param extPropFiles void
     */
    public void setExtPropFiles(Set<String> extPropFiles) {
        // 当前ClassPath的绝对URI路径
        String webAppConfigKey = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.info("classpath file ...{}", webAppConfigKey);
        Properties properties = new Properties();
        for (String extPropFile : extPropFiles) {
            extPropFile = extPropFile.replaceAll("classpath:", webAppConfigKey); // 替换路径

            try {
                logger.info("Loading properites file from " + extPropFile);

                Properties prop = new Properties();
                Resource result = new FileSystemResource(extPropFile);

                prop.load(new InputStreamReader(result.getInputStream(), "UTF-8"));
                properties.putAll(prop);
                
            } catch (Exception e) {
                logger.error("extPropFiles error", e);
            }
        }
        this.setProperties(properties); //设置
        ExtPropertyPlaceholderConfigurer.properties = new Properties[] { properties }; // 同步
    }

    /**
     * 根据key获取value值;若获取为空返回defaultValue默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getValue(String key, String defaultValue) {
        String value = null;
        Object obj = null;
        if (properties == null) {
            throw new RuntimeException("配置文件未加载!");
        }
        if (properties != null) {
            for (Properties properties : properties) {
                // 根据key获取value值
                obj = properties.getProperty(key, defaultValue);
                if (obj != null) {
                    value = (String) obj;
                    break;
                }
            }
        }
        // 若没有则取默认值
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }


}
