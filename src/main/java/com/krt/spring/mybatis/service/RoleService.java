package com.krt.spring.mybatis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krt.spring.mybatis.entity.Role;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.mapper.BatchDao;
import com.krt.spring.mybatis.mapper.MenuMapper;
import com.krt.spring.mybatis.mapper.RoleMapper;
import com.krt.spring.mybatis.mapper.UserMapper;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired BatchDao batchDao;

	@Autowired
	private MenuMapper menuMapper;
	
	
	public List<Role> getAllRole(){
		
		return roleMapper.findAll();
	}
	
	public Role getRole(Integer id){
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public void updateRole(Role role) {
		if(role.getId()==null){
			roleMapper.insert(role);
		}else{
			roleMapper.updateByPrimaryKey(role);
		}
	}
	
	public void deleteRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
//		删除中间表对应关系
//		RoleMapper.deleteByRoleId(id); 
	}
	
	public List<User> getUsersByRoleId(List<Integer> ids){
		List<Integer> userIds = userMapper.selectUserId(ids);
		if(userIds.size()>0){
			return userMapper.selectAllIn(userIds);
		}
		return new ArrayList<User>();
		
	}
	public void allocatedusersave(Long roleId, Long[] userids){
		userMapper.deleteByRoleId(roleId);
		if(userids!=null){
			batchDao.assign(roleId, userids);
		}
	}
	
	public void saveAssignUser(Long roleId, Long[] userids){
		Map map=new HashMap<>();
		map.put("roleId", roleId);
		map.put("userIds", userids);
		roleMapper.insertAssignUserMap(map);
	}
	
	public List<Integer> getMenuIdsByRoleId(List<Integer> ids) {
		return roleMapper.selectMenuId(ids);
	}
	
	public void allocatedmenusave(Long roleId, Long[] menuids) {
		menuMapper.deleteByRoleId(roleId);
		if(menuids!=null){
			batchDao.assignMenu(roleId, menuids);
		}
		
	}
	
}
