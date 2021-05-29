/**
 * Created 2017-01-26 Copyright shashijie modified by <date> 2017-01-26 <user>shashijie
 * <description>
 */
package sy.exception;

import com.alibaba.druid.support.json.JSONUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常捕获类
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-26
 * @since JDK 1.7
 */
public class ExceptionUtil implements ThrowsAdvice, HandlerExceptionResolver {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    /**
     * shashijie 2017-01-27 异常记录简单方法:优先级高,实现ThrowsAdvice接口
     * @param ex void
     */
    /* public void afterThrowing(RuntimeException ex) { logger.error("切面捕获异常 ----"); } */

    /**
     * shashijie 2017-01-27 异常记录详细方法:优先级低,实现ThrowsAdvice接口
     * @param method 方法
     * @param params 入参类型实例
     * @param object 类实例
     * @param subclass 异常明细
     * @return Object
     * @throws Exception
     */
    public void afterThrowing(Method method, Object params, Object object, Throwable subclass) {
        logger.error("切面捕获异常 ---------\r\n类:{} \r\n入参值:{} \r\n方法实例:{} \r\n异常明细:", method, params, object,
                subclass);
    }

    /**
     * shashijie 2017-01-27 当ThrowsAdvice, HandlerExceptionResolver两个接口一起实现是先走afterThrowing方法
     * 异常处理方法,在SpringMVC中，所有用于处理在请求映射和请求处理过程中抛出的异常的类，都要实现HandlerExceptionResolver接口。
     * HandlerExceptionResolver接口有一个方法resolveException，当controller层出现异常之后就会进入到这个方法resolveException。
     * @param request
     * @param response
     * @param object
     * @param exception
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object object, Exception exception) {
        // 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf(
                "XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("errorCode", ((BusinessException) exception).getErrorCode());
                map.put("errorDesc", exception.getMessage());
            } else {
                map.put("errorCode", "系统异常！");
                map.put("errorDesc", exception.getMessage());
            }
            // 这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
            logger.error("切面捕获异常 ----\r\n类:{} \r\n异常明细:", object, exception.getMessage());
            // 对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("/error", map);
        } else {
            // 如果是ajax请求，JSON格式返回
            PrintWriter writer = null;
            try {
                response.setContentType("application/json;charset=UTF-8");
                writer = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof BusinessException) {
                    map.put("errorCode", ((BusinessException) exception).getErrorCode());
                    map.put("errorDesc", exception.getMessage());
                } else {
                    map.put("errorCode", "系统异常！");
                    map.put("errorDesc", exception.getMessage());
                }
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
            } catch (IOException e) {
                logger.error("切面捕获异常 ----\r\n类:{} \r\n异常明细:", object, e.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        return null;
    }

}
