package com.gz.javastudy.springapp.dao.impl;

import com.gz.javastudy.springapp.dao.StudentDao;
import com.gz.javastudy.springapp.domain.Student;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author gaozhen
 * @title: StudentDao1
 * @projectName simple-spring
 * @description: studentdao非单例的实现
 * @date 2019-10-19 15:34
 */
@Repository
@Scope("prototype")
public class StudentDaoPrototypeImpl implements StudentDao {

    @Override
    public Student getStudentById(long id) {
        System.out.println("student1");
        return null;
    }
}
