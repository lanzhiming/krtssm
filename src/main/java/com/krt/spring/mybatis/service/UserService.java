package com.krt.spring.mybatis.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krt.spring.mybatis.entity.Menu;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.mapper.MenuMapper;
import com.krt.spring.mybatis.mapper.RoleMapper;
import com.krt.spring.mybatis.mapper.UserMapper;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;
	
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
	
	public List<Menu> getCurrentMenu(Integer id){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(id);
		List<Integer> roleIds = userMapper.selectRoleId(ids);
		if(roleIds!=null&&roleIds.size()>0){
			List<Integer> allmenuIds = roleMapper.selectMenuId(roleIds);
			Set<Integer> menuIds = new HashSet<Integer>(allmenuIds);
			if(allmenuIds!=null&&allmenuIds.size()>0){
				return menuMapper.selectAllIn(new ArrayList<Integer>(menuIds));
			}
			
		}
		return new ArrayList<Menu>();
		
	}
	
}
