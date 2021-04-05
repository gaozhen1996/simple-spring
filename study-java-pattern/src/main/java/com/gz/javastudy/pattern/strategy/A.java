package com.gz.javastudy.pattern.strategy;

/**
 * @author gaozhen
 * @title: A
 * @projectName study-java
 * @description: 绩效为A
 * @date 2021/4/5上午10:38
 */
public class A implements Strategy{

    @Override
    public void getResult() {
        System.out.println("绩效：A");
    }
}
