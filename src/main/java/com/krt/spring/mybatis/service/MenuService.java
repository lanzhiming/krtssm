package com.krt.spring.mybatis.service;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krt.spring.mybatis.entity.Menu;
import com.krt.spring.mybatis.mapper.MenuMapper;

@Service
@Transactional
public class MenuService {
	
	@Autowired MenuMapper menuMapper;
	
	public List<Menu> getAllMenu() {
		return menuMapper.selectAll();
	}

	public Menu getMenu(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	public void updateMenu(Menu menu) {
		if (menu.getId()==null) {
			menuMapper.insert(menu);
		}else{
			menuMapper.updateByPrimaryKey(menu);
		}
		
	}

	public void deleteMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}
	
	
	public int deleteMenuCheck(Integer id) {
		List<Integer> ids=Lists.newArrayList();
		ids.add(id);
		if(menuMapper.selectRoleId(ids).size()>0&&menuMapper.selectByPId(id)!=null){
			return 3;
		}
		if(menuMapper.selectRoleId(ids).size()>0){
			return 1;
		}
		if(menuMapper.selectByPId(id)!=null){
			return 2;
		}
		return 0;
	}
	
}
