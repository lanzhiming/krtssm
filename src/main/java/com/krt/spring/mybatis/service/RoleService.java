package com.krt.spring.mybatis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krt.spring.mybatis.entity.Role;
import com.krt.spring.mybatis.mapper.RoleMapper;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleMapper RoleMapper;
	
	
	public List<Role> getAllRole(){
		
		return RoleMapper.findAll();
	}
	
	public Role getRole(Integer id){
		return RoleMapper.selectByPrimaryKey(id);
	}
	
	public void updateRole(Role Role) {
		if(Role.getId()==null){
			RoleMapper.insert(Role);
		}else{
			RoleMapper.updateByPrimaryKey(Role);
		}
	}
	
	public void deleteRole(Integer id) {
		RoleMapper.deleteByPrimaryKey(id);
//		删除中间表对应关系
//		RoleMapper.deleteByRoleId(id); 
	}
	
}
