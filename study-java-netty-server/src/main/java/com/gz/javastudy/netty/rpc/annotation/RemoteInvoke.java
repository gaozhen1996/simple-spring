package com.gz.javastudy.netty.rpc.annotation;

import java.lang.annotation.*;

//此注解用于远程调用的接口属性上
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteInvoke {
}
