package com.gz.javastudy.mybatisapp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.gz.javastudy.mybatisapp.domain.Student;

@Mapper
public interface StudentDao {
	public Student selectStudent(String name);
}
