/**
 * Created 2017-01-20 Copyright shashijie modified by <date> 2017-01-20 <user>shashijie
 * <description>
 */
package sy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 测试拦截器
 * @description: 拦截器可以不直接实现HandlerInterceptor接口，
 *               而是扩展实现了HandlerInterceptor接口的具体类HandlerInterceptorAdapter ， 这样的话我们不需要把上面5个方法都实现 ，
 *               而只需要override我们需要的方法就可以了
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-20
 * @since JDK 1.7
 */
/* Spring容器启动时加载此拦截器 */
@Component
public class TextInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(TextInterceptor.class);

    /**
     * shashijie 2017-01-20 拦截request请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.debug("request请求拦截perHandle");
        System.out.println("~~~~~~~~~~~~~~~~~~一般判断登录用户名operatorNo是否存在,"
                + "存在返回true,否则返回false终止请求~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return super.preHandle(request, response, handler);
    }

    /**
     * shashijie 2017-01-20 拦截response返回,该方法需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.debug("response返回拦截postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * shashijie 2017-01-20 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        logger.debug("response返回拦截afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

}
