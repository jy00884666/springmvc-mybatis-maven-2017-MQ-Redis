/**
 * Copyright (c) 2015, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd. All right reserved. THIS IS
 * UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT MANAGEMENT CO., LTD. THE CONTENTS OF
 * THIS FILE MAY NOT BE DISCLOSED TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN
 * PART, WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT CO., LTD.
 */

package sy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 并发控制的注解
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-25
 * @since JDK 1.7
 */
// Target 设置注解权限,定义此注解可以申明在哪里 METHOD:方法上,TYPE:类上
@Target({ ElementType.METHOD, ElementType.TYPE })
// Retention生命周期 Runtime:运行时
@Retention(RetentionPolicy.RUNTIME)
// 注解自动继承,类和子类都包含这个注解
@Inherited
public @interface Sync {

    /**
     * shashijie 2017-01-25 表示:同步锁的KEY值,若没有默认为方法名
     * @return String
     */
    public String name() default "";

    /**
     * shashijie 2017-01-25 表示:同步锁的key = 方法名+第几个入参的值,数组形式可以是多个
     * @return int[]
     */
    public int[] paramIndexs() default {};

}
