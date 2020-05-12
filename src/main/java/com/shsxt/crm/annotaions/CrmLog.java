package com.shsxt.crm.annotaions;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("all")
public @interface CrmLog {
    String oper() default "";
    String module() default "";
}
