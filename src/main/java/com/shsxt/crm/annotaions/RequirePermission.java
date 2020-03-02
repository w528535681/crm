package com.shsxt.crm.annotaions;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("all")
public @interface RequirePermission {
    String code() default "";
}
