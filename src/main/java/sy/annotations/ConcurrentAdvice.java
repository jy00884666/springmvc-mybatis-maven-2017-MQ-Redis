/**
 * Copyright (c) 2015, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd. All right reserved. THIS IS
 * UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT MANAGEMENT CO., LTD. THE CONTENTS OF
 * THIS FILE MAY NOT BE DISCLOSED TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN
 * PART, WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT CO., LTD.
 */

package sy.annotations;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sy.exception.BusinessException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author shashijie
 * @description: 并发控制切面, 继承方法拦截器MethodInterceptor
 * @reason: ADD REASON(可选)
 * @date 2017-01-25
 * @since JDK 1.7
 */
public class ConcurrentAdvice implements MethodInterceptor {
    
    private Map<String, AtomicBoolean> isRunMap = new HashMap<String, AtomicBoolean>();
    
    private static final Logger logger = LoggerFactory.getLogger(ConcurrentAdvice.class);
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.debug("进入并发控制切面");
        // 在这个Boolean值的变化的时候不允许在之间插入，保持操作的原子性方法和举例
        AtomicBoolean isRun = null;
        // 获取方法(接口)上的Sync注解,若无返回null
        Sync annotation = methodInvocation.getMethod().getAnnotation(Sync.class);
        if (annotation != null) {
            // 通过算法获取key,默认用类名+"."+方法全路径声明
            String key = generateKey(annotation, methodInvocation);
            logger.debug("需要进行并发控制,key={}", key);
            isRun = isRunMap.get(key);
            if (isRun == null) {
                // 两次判定,确保单例模式
                synchronized (this) {
                    isRun = isRunMap.get(key);
                    if (isRun == null) {
                        isRun = new AtomicBoolean(false);
                        isRunMap.put(key, isRun);
                    }
                }
            }
            // 若为true表示有其他进程正在执行同步方法
            if (isRun.get()) {
                throw new BusinessException("业务异常", "不能并发执行该方法！");
            }
            /**
             * 比较AtomicBoolean和第一个参数的的值,如果一致再把AtomicBoolean的值设成第二个参数,成功返回true,
             * 最重要的是这两件事是原子操作这两个动作之间不会被打断,任何内部或者外部的语句都不可能在两个动作之间运行。
             * 为多线程的控制提供了解决的方案,具体方法说明清查api文档,在这里不多加描述。
             */
            // 若修改isRun的值为true失败,则表名有其他线程已经启动执行同步方法
            else if (!isRun.compareAndSet(false, true)) {
                throw new BusinessException("业务异常", "不能并发执行该方法！");
            }
        }
        
        try {
            // 执行该方法
            return methodInvocation.proceed();
        } finally {
            if (isRun != null) {
                isRun.set(false);
            }
        }
        
    }
    
    /**
     * shashijie 2017-01-25 获取key,默认用类名+"."+方法全路径声明
     * @param annotation
     * @param methodInvocation
     * @return
     * @throws NoSuchAlgorithmException String
     */
    private String generateKey(Sync annotation, MethodInvocation methodInvocation)
            throws NoSuchAlgorithmException {
        // 注解中的name属性值
        String key = annotation.name();
        logger.debug("name:{}", key);
        // 注解中的paramIndexs属性值int[]数组
        int[] paramIndexs = annotation.paramIndexs();
        logger.debug("paramIndexs:{}", paramIndexs);
        // 接口方法的入参参数
        StringBuffer sb = new StringBuffer();
        if (paramIndexs != null && paramIndexs.length > 0) {
            // 获取方法中所有入参的值的数组形式
            Object[] args = methodInvocation.getArguments();
            for (int paramIndex : paramIndexs) {
                if (paramIndex <= args.length) {
                    sb.append(args[paramIndex - 1]);
                }
            }
        }
        logger.debug("辅助参数:{}", sb.toString());
        // 若没有name属性值,则用类名+"."+方法全路径声明,自动构建,例如
        /* Method.getDeclaringClass() 返回interface sy.service.TextService
         * -------------------------------------------华丽的分割线----------------------------------------
         * Method.toGenericString() 返回 public abstract int
         * sy.service.TextService.deleteByPrimaryKey(java.math.BigDecimal) */
        if (StringUtils.isBlank(key)) {
            // 系统安装自己的规则生成key
            key = md5(methodInvocation.getMethod().getDeclaringClass() + "."
                    + methodInvocation.getMethod().toGenericString() + sb.toString());
        } else if (key.length() + sb.length() > 32) {
            // 过长时MD5
            key = md5(key + sb.toString());
        } else {
            key = key + sb.toString();
        }
        return key;
    }
    
    /**
     * shashijie 2017-01-25 加密方法
     * @param str
     * @return
     * @throws NoSuchAlgorithmException String
     */
    private static String md5(String str) throws NoSuchAlgorithmException {
        byte[] buf = str.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(buf);
        byte[] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : tmp) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }
    
}
