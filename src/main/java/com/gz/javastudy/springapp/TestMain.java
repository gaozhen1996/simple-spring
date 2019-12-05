package com.gz.javastudy.springapp;


import com.gz.javastudy.springapp.imports.EnableAOP;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gz.javastudy.springapp.common.MapperScan;
import com.gz.javastudy.springapp.custom.CustomBeanFactoryPostProcessor;
import com.gz.javastudy.springapp.dao.StudentDao;
import com.gz.javastudy.springapp.service.StudentService;

/**
 * @title: Test
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-19 15:37
 */
//@ComponentScan("com.gz.javastudy.spring")
@ComponentScan("com.gz.javastudy.springapp")
@Configuration
@MapperScan
//@EnableAOP
public class TestMain {

    public static void main(String[] args) {
        //======================================测试单例对象中，含有非单例的属性===========================================
        testingletonSonjectPrototypeField();
        //===================================模仿mybatis，将接口注册到spring容器中=======================================
//        testSelfMyBatis();
        //===================================执行自定义的BeanFactoryPostProcessor====================================
//        testExecuteCustomBeanFactoryPostProcessor();
        //=======================模仿AOP,测试在方法前执行自定义的代码,注意：测试这个方法需要加上@EnableAOP注解========================
//        testMyAop();
    }

    /**
     * 测试单例对象中，含有非单例的属性
     */
    private static void testingletonSonjectPrototypeField(){
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        System.out.println("======================================测试单例对象中，含有非单例的属性==========================================");
        StudentService studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();

        studentService = (StudentService) context.getBean("studentService");
        studentService.getStudentById();
        context.close();
    }

    /**
     * 模仿AOP,测试在方法前执行自定义的代码
     */
    private static void testMyAop(){
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        System.out.println("===================================模仿AOP,测试在方法前执行自定义的代码==========================================");
        StudentDao studentDao = (StudentDao) context.getBean("studentDaoImpl1");
        studentDao.getStudentById(100001);
        context.close();
    }
    
    /**
     * 此方法的测试，详细请见 https://github.com/gaozhen1996/study-note/blob/master/JavaEE/spring/spring%E6%B5%81%E7%A8%8B.md#2%E6%B5%81%E7%A8%8B-1
     */
    private static void testExecuteCustomBeanFactoryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addBeanFactoryPostProcessor(new CustomBeanFactoryPostProcessor());
        context.register(TestMain.class);
        context.refresh();
        context.close();
    }
    
    /**
     * 模仿mybatis，将接口注册到spring容器中
     */
    private static void testSelfMyBatis(){
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        System.out.println("===================================模仿mybatis，将接口注册到spring容器中=======================================");
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        studentDao.getStudentById(100001);
        context.close();
    }

}
