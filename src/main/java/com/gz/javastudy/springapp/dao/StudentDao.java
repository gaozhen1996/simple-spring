package com.gz.javastudy.springapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gz.javastudy.springapp.domain.Student;

/**
 * @author gaozhen
 * @title: StudentDao
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:32
 */
@Mapper
public interface StudentDao {
    Student getStudentById(long id);
}
