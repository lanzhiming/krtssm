package com.krt.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.Dictionary;
import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.User;

import java.util.List;
@MyBatisDao
public interface DictionaryMapper {
	public List<Dictionary> findAll();
	public Dictionary selectByPrimaryKey(Integer id);
	public int insert(Dictionary record);
	public int update(Dictionary record);

	Integer checkCode(@Param("type") String type, @Param("code") String code, @Param("id") Integer id);

	void deleteChildDic(@Param("id") Integer id);

	List selectChildList(@Param("id") Integer id);

	void deleteByType(@Param("type") String type) throws Exception;

	List selectDicByPidAndType(@Param("pid") Integer pid, @Param("type") String type);
	
}