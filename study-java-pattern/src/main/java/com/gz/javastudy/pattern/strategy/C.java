package com.gz.javastudy.pattern.strategy;

/**
 * @author gaozhen
 * @title: C
 * @projectName study-java
 * @description: 绩效为C
 * @date 2021/4/5上午10:41
 */
public class C implements Strategy{

    @Override
    public void getResult() {
        System.out.println("绩效C");
    }
}
