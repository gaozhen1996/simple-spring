package com.gz.javastudy.springapp;


import com.gz.javastudy.springapp.common.MapperScan;
import com.gz.javastudy.springapp.common.MyImportBeanDefinitionRegistrar;
import com.gz.javastudy.springapp.dao.StudentDao;
import com.gz.javastudy.springapp.service.StudentService;
import org.springframework.context.annotation.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @title: Test
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:37
 */
//@ComponentScan("com.gz.javastudy.spring")
@ComponentScan("com.gz.javastudy.springapp")
@Configuration
@MapperScan
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        studentDao.getStudentById();
        testingletonSonjectPrototypeField(context);
        context.close();
    }

    private static void testingletonSonjectPrototypeField(AnnotationConfigApplicationContext context){
        System.out.println("======================================测试单例对象中，含有非单例的属性==========================================");
        StudentService studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();

        studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();
    }

}