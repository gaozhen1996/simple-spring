package com.gz.javastudy.netty.controller;
import com.gz.javastudy.netty.annotation.Remote;
import org.springframework.stereotype.Controller;

@Controller
@Remote
public class UserController implements IUserController{

    //此方法用于远程调用，需要加上Remote注解
    @Override
    @Remote("getUserNameById")
    public Object getUserNameById(String userId){
        //此处并未访问数据库，只是做简单输出
        System.out.println("客户端请求的用户id为======"+userId);
        return "响应结果===getUserNameById:"+userId;
    }

    @Override
    @Remote("getUserById")
    public Object getUserById(String userId) {
        //此处并未访问数据库，只是做简单输出
        System.out.println("客户端请求的用户id为======"+userId);
        return "响应结果===getUserById:"+userId;
    }
}
