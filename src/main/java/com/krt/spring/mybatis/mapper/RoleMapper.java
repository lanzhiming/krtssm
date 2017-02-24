package com.krt.spring.mybatis.mapper;

import java.util.List;

import com.krt.spring.mybatis.MyBatisDao;
import com.krt.spring.mybatis.entity.Role;

@MyBatisDao
public interface RoleMapper {
	public List<Role> findAll();
	public Role selectByPrimaryKey(Integer id);
	public int insert(Role record);
	public int updateByPrimaryKey(Role record);
	public int deleteByPrimaryKey(Integer id);
}
