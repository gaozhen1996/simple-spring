package com.gz.javastudy.springapp;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @title: Test
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:37
 */
@ComponentScan("com.gz.javastudy.spring")
//@ComponentScan("com.gz.javastudy.springapp")
@Configuration
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        StudentService studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();

        studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();
    }
}