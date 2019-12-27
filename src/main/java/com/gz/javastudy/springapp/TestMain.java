package com.gz.javastudy.springapp;


import com.gz.javastudy.springapp.imports.EnableAOP;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gz.javastudy.springapp.common.MapperScan;
import com.gz.javastudy.springapp.config.ComponentConfig;
import com.gz.javastudy.springapp.config.ConfigurationConfig;
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
@EnableAOP
public class TestMain {

    public static void main(String[] args) {
    	int flag  = 5;
    	switch (flag) {
		case 1:
			//======================================测试单例对象中，含有非单例的属性========================================
	        testingletonSonjectPrototypeField();
			break;
		case 2:
	        //===================================模仿mybatis，将接口注册到spring容器中======================================
	        testSelfMyBatis();
			break;
		case 3:
	        //===================================执行自定义的BeanFactoryPostProcessor==========================
	        testExecuteCustomBeanFactoryPostProcessor();
			break;
		case 4:
	        //======================模仿AOP,测试在方法前执行自定义的代码,注意：测试这个方法需要加上@EnableAOP注解====================
	        testMyAop();
			break;
		case 5:
	        //=================================测试@Configuration与@Component的区别=====================================
			testConfigurationORComponentDiff();
			break;			
		}
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
        StudentDao studentDao = (StudentDao) context.getBean("studentDaoPrototypeImpl");
        studentDao.getStudentById(100001);
        context.close();
    }
    
    /**
     * 此方法的测试，详细请见https://github.com/gaozhen1996/study-note/blob/master/JavaEE/spring/spring%E6%BA%90%E7%A0%812-%E6%89%AB%E6%8F%8Fbean.md#postprocessorregistrationdelegatejava
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

    /**
     * 测试@Configuration与@Component的区别
     */
    private static void testConfigurationORComponentDiff() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        System.out.println("=================================测试@Configuration与@Component的区别=====================================");
        ConfigurationConfig configurationConfig  = (ConfigurationConfig) context.getBean("configurationConfig");
        System.out.println("使用@Configuration");
        System.out.println(configurationConfig);
        System.out.println(configurationConfig.getConfigReader()==configurationConfig.getConfigReader());
        ComponentConfig componentConfig  = (ComponentConfig) context.getBean("componentConfig");
        System.out.println("使用@Component");
        System.out.println(componentConfig.getConfigReader()==componentConfig.getConfigReader());
        System.out.println(componentConfig);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("加载文件请输入1，退出请输入0");
            String key = scanner.nextLine();
            if(key.equals("0")) {
            	scanner.close();
            	context.close();
            	System.exit(0);
            }
            System.out.println(configurationConfig.getConfigReader().getconfigValueByKey("scanPackage"));
			System.out.println(componentConfig.getConfigReader().getconfigValueByKey("scanPackage"));
		}
	}
}
