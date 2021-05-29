package sy.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sy.utils.Mirror;

public class AbstractBaseController {

    private final static Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);

    /**
     * 获得真实跳转路径
     * @param request
     * @param view
     * @return
     */
    public String getRealView(HttpServletRequest request, String view) {
        String servletPath = request.getServletPath();
        logger.info(String.format("来自%s访问。", servletPath));
        return servletPath.substring(1, servletPath.lastIndexOf("/") + 1) + view;
    }

    /**
     * 绑定参数到POJO
     * @param <T>
     * @param request
     * @param clazz
     * @return
     */
    public <T> T bindRequestParam(HttpServletRequest request, Class<T> clazz) {
        return bindRequestParams(request, clazz);
    }

    /**
     * 绑定参数到POJO
     * @param <T>
     * @param request
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T bindRequestParams(HttpServletRequest request, Class<T> clazz) {
        try {
            String jsonParams = (String) request.getParameter("jsonparams");
            if (jsonParams != null) {
                // json方式的参数
                logger.debug("request paramters(json):" + jsonParams);
                return (T) JSONObject.toBean(JSONObject.fromObject(jsonParams), clazz);
            } else {
                T bean = clazz.newInstance();
                // 普通request attribute传参
                logger.debug("request paramters :");
                // 获取类中所有属性:Mirror自定义获取类
                for (Field field : Mirror.getAllFields(clazz)) {
                    // 属性名称
                    String fieldName = field.getName();
                    logger.debug("-----fieldName:" + fieldName);
                    Object value = null;
                    // 通过不同类型获取request的传入值
                    if (field.getType().isArray()) {// 如果是数组参数
                        value = request.getParameterValues(fieldName);
                    } else if ((field.getType()) == List.class) {// 如果是数组参数
                        value = request.getParameterValues(fieldName);
                    } else {
                        value = request.getParameter(fieldName);
                    }
                    // 如果Long型字段传值不为Long时，跳过解析
                    if (!StringUtils.isEmpty(value) && field.getType() == Long.class) {
                        try {
                            Long.parseLong((String) value);
                        } catch (NumberFormatException e) {
                            logger.error("字段赋值错误,Name:" + fieldName + ",Type:" + field.getType() + ",value:"
                                    + value);
                            value = null;
                        }
                    }
                    // 如果传入值非空则调用set方法赋入对象值
                    if (value != null && !StringUtils.isEmpty(value)) {
                        try {
                            // 获取属性对应的类型:Mirror自定义获取类
                            value = Mirror.parseObj(field.getType(), value);
                            // 获取属性对饮的set方法:toUpperCaseFirstOne()首字母大写转换方法
                            Method method = clazz.getMethod("set" + toUpperCaseFirstOne(fieldName),
                                    field.getType());
                            // 调用
                            method.invoke(bean, value);

                            logger.debug(fieldName + ":" + value + ",");
                        } catch (Exception e) {

                            e.printStackTrace();
                            logger.error("属性设置失败,Name:" + fieldName + ",Type:" + field.getType() + ",value:"
                                    + value, e);
                        }
                    }
                }

                return bean;
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error("绑定参数到POJO失败", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error("绑定参数到POJO失败", e);
        }
        logger.debug("绑定参数到POJO失败！");
        return null;
    }

    /**
     * 首字母转大写 actDt -> AckDt
     * @author guopeng.li
     * @param s
     * @return
     */
    private static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1))
                    .toString();
        }
    }

}
