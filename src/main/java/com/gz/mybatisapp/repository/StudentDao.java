package com.gz.mybatisapp.repository;

import com.gz.mybatisapp.domain.Student;

public interface StudentDao {
	public Student selectStudent(String name); 
}
