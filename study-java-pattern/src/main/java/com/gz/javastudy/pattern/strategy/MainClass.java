package com.gz.javastudy.pattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaozhen
 * @title: MainClass
 * @projectName study-java
 * @description: 测试入口类
 * 用策略模式改造if多分支判断
 * @date 2021/4/5上午10:43
 */
public class MainClass {
    public static void main(String[] args) {
        Map<Integer,Strategy> strategyMap = new HashMap<>();
        strategyMap.put(10,new A());
        strategyMap.put(9,new A());
        strategyMap.put(8,new B());
        strategyMap.put(7,new C());

        int score = 78;
        strategyMap.get(score/10).getResult();
    }
}
