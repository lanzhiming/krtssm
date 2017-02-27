package com.krt.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.krt.spring.mybatis.MyBatisDao;

import java.util.List;
@MyBatisDao
public interface DictionaryMapper {

	Integer checkCode(@Param("type") String type, @Param("code") String code, @Param("id") Integer id);

	void deleteChildDic(@Param("id") Integer id);

	List selectChildList(@Param("id") Integer id);

	void deleteByType(@Param("type") String type) throws Exception;

	List selectDicByPidAndType(@Param("pid") Integer pid, @Param("type") String type);
	
}