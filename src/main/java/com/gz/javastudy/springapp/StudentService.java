package com.gz.javastudy.springapp;

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

//    @Autowired
//    public StudentDao studentDao1;

    @Lookup("studentDao1")
    public abstract StudentDao getStudentDao();

    public void getStudentById(){
        System.out.println("dao="+getStudentDao());
        System.out.println(this.hashCode());
        System.out.println(this);
        getStudentDao().getStudentById();
    }
}
