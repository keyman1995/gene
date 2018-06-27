package com.ycjcjy.gene.web.action.system.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by feng on 2017/8/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatToken {

    boolean apply() default false;

    boolean valid() default false;
}
