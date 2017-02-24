package com.krt.spring.mybatis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.mapper.UserMapper;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User findUserByUsername(String username){
		return userMapper.findByUsername(username);
	}
	
	public List<User> getAllUser(){
		
		return userMapper.findAll();
	}
	
	public User getUser(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}
	
	public void updateUser(User user) {
		if(user.getId()==null){
			userMapper.insert(user);
		}else{
			userMapper.updateByPrimaryKey(user);
		}
	}
	
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
//		删除中间表对应关系
//		userMapper.deleteByUserId(id); 
	}
	
}
