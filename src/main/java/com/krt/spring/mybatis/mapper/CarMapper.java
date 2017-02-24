package com.krt.spring.mybatis.mapper;

import java.util.List;

import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.Car;

@MyBatisDao
public interface CarMapper {
	public List<Car> findAll();
}
