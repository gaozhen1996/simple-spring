package com.gz.javastudy.springapp.service;

import com.gz.javastudy.springapp.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * @author gaozhen
 * @title: StudentService
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:31
 */
@Service
//@Scope("prototype")
public abstract class StudentService {

    @Autowired
    public StudentDao studentDaoSingleImpl;

    /**
     *使用这种方法，可以在一个单例的bean对象中，属性是非单例的
     */
    @Lookup("studentDaoPrototypeImpl")
    public abstract StudentDao getStudentDao();

    public void getStudentById(){
    	System.out.println("注入单例的属性StudentDao="+studentDaoSingleImpl);
        System.out.println("注入非单例的属性StudentDao="+getStudentDao());
        System.out.println(this);
        getStudentDao().getStudentById(100001);
    }
}
