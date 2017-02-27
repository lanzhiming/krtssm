package com.krt.spring.mybatis.mapper;

import java.util.List;


import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.DictionaryType;

@MyBatisDao
public interface DictionaryTypeMapper{
	public List<DictionaryType> findAll();
	Integer checkCode(String code,Integer id);
	public void deleteByPrimaryKey(Integer id) throws Exception;
   
}