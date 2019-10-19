package com.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author gaozhen
 * @title: StudentDao1
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:34
 */
@Repository
@Scope("prototype")
public class StudentDao1 implements StudentDao{

    @Override
    public void getStudentById() {
        System.out.println("student1");
    }
}
