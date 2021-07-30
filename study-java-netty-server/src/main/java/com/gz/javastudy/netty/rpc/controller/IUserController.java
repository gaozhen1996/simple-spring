package com.gz.javastudy.netty.rpc.controller;

public interface IUserController {
    Object getUserNameById(String userId);

    Object getUserById(String userId);
}
