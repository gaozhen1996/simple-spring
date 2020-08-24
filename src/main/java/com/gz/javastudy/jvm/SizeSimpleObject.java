package com.gz.javastudy.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author gaozhen
 * @title: SimpleObject
 * @projectName study-java
 * @description: 简单对象内存分析
 * @date 2020-08-21 23:35
 */
public class SizeSimpleObject {

    int a = 0;

    public static void main(String[] args) {
        SizeSimpleObject object = new SizeSimpleObject();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}
