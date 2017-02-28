package com.krt.spring.mybatis.mapper;

import java.util.List;


import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.Dictionary;
import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.User;

@MyBatisDao
public interface DictionaryTypeMapper{
	public List<DictionaryType> findAll();
	public DictionaryType selectByPrimaryKey(Integer id);
	Integer checkCode(String code,Integer id);
	public void deleteByPrimaryKey(Integer id) throws Exception;
	public int insert(DictionaryType record);
	public int update(DictionaryType record);
   
}