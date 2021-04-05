package com.gz.javastudy.pattern.strategy;

/**
 * @author gaozhen
 * @title: B
 * @projectName study-java
 * @description: 绩效为B
 * @date 2021/4/5上午10:40
 */
public class B implements Strategy{
    @Override
    public void getResult() {
        System.out.println("绩效：B");
    }
}
