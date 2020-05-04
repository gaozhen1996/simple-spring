package com.gz.javastudy.springapp.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaozhen
 * @title: StudentDao
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-1915:32
 */
@Mapper
public interface StudentDao {
    void getStudentById(long id);
}
