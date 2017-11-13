package com.example;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by husongzhen on 17/11/10.
 */
@Target(METHOD)
@Retention(SOURCE)
public @interface LayoutId {
    String value();
    String path();

}
