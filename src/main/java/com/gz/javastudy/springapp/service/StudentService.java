package com.gz.javastudy.springapp.service;

import com.gz.javastudy.springapp.dao.StudentDao;
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

    /**
     *使用这种方法，可以在一个单例的bean对象中，属性是非单例的
     */
    @Lookup("studentDaoImpl1")
    public abstract StudentDao getStudentDao();

    public void getStudentById(){
        System.out.println("属性dao="+getStudentDao());
        System.out.println(this.hashCode());
        System.out.println(this);
        getStudentDao().getStudentById();
    }
}
