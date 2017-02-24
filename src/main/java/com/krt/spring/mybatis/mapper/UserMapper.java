package com.krt.spring.mybatis.mapper;

import java.util.List;

import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.User;

@MyBatisDao
public interface UserMapper {
	public List<User> findAll();
	public User findByUsername(String username);
	public User selectByPrimaryKey(Integer id);
	public int insert(User record);
	public int updateByPrimaryKey(User record);
	public int deleteByPrimaryKey(Integer id);
}
