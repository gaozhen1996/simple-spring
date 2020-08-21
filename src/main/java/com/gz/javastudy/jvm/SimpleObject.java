package com.gz.javastudy.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author gaozhen
 * @title: SimpleObject
 * @projectName study-java
 * @description: 简单对象内存分析
 * @date 2020-08-2123:35
 */
public class SimpleObject {

    int a = 0;
    int b = 0;

    public static void main(String[] args) {
        SimpleObject object = new SimpleObject();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}
