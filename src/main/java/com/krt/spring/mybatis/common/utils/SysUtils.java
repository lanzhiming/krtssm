package com.krt.spring.mybatis.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.krt.spring.mybatis.entity.Menu;
import com.krt.spring.mybatis.service.MenuService;
import com.krt.spring.mybatis.service.RoleService;

public class SysUtils {
	
	public static final String SYS_CACHE = "sysCache";
	public static final String CACHE_MENU_LIST_R = "menuListForRid_";
	public static final String CACHE_MENU_LIST = "menuList";
	
	public static Cache getSpringCache(String cacheName){
		CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
		return cacheManager.getCache(cacheName);
	}
	
	/**
	 * 获取角色的菜单数据
	 * @param id
	 * @return
	 */
	public static List<Menu> getMenuListForR(Integer id){
		ValueWrapper valueWrapper = getSpringCache(SYS_CACHE).get(CACHE_MENU_LIST_R+id);
		if (valueWrapper == null){
			MenuService menuService = SpringContextHolder.getBean(MenuService.class);
			RoleService roleService = SpringContextHolder.getBean(RoleService.class);
			List<Menu> menus = menuService.getAllMenu();
			List<Menu> list = new ArrayList<Menu>();
			sortList(list, menus, 0, true);
			List<Integer> ids=new ArrayList<Integer>();
			ids.add(id);
			List<Integer> currentMenuIds = roleService.getMenuIdsByRoleId(ids);
			wrapZtreeData(currentMenuIds, list);
			getSpringCache(SYS_CACHE).put(CACHE_MENU_LIST_R+id, list);
			return list;
		}else{
			return (List<Menu>) valueWrapper.get();
		}
	}
	
	public static List<Menu> getMenuListForRole(Integer id){
		MenuService menuService = SpringContextHolder.getBean(MenuService.class);
		RoleService roleService = SpringContextHolder.getBean(RoleService.class);
		List<Menu> menus = menuService.getAllMenu();
		List<Menu> list = new ArrayList<Menu>();
		sortList(list, menus, 0, true);
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(id);
		List<Integer> currentMenuIds = roleService.getMenuIdsByRoleId(ids);
		wrapZtreeData(currentMenuIds, list);
		return list;
	}
	
	public static List<Menu> findAllMenuList(){
		MenuService menuService = SpringContextHolder.getBean(MenuService.class);
		List<Menu> menus = menuService.getAllMenu();
		ArrayList<Menu> list = new ArrayList<Menu>();
		sortList(list, menus, 0, true);
		return list;
	}
	
	/**
	 * 获取所有的菜单数据
	 * @return
	 */
	public static List<Menu> getAllMenuList(){
		ValueWrapper valueWrapper = getSpringCache(SYS_CACHE).get(CACHE_MENU_LIST);
		if (valueWrapper == null){
			MenuService menuService = SpringContextHolder.getBean(MenuService.class);
			List<Menu> menus = menuService.getAllMenu();
			ArrayList<Menu> list = new ArrayList<Menu>();
			sortList(list, menus, 0, true);
			getSpringCache(SYS_CACHE).put(CACHE_MENU_LIST, list);
			return list;
		}else{
			return (List<Menu>) valueWrapper.get();
		}
	}
	
	public static void sortList(List<Menu> list, List<Menu> sourcelist, Integer parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if ( e.getParentId()!=null
					&& e.getParentId().equals(String.valueOf(parentId))){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParentId()!=null
								&& child.getParentId().equals(String.valueOf(e.getId()))){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param currentMenuIds 当前角色包含的菜单id集合
	 * @param menus 菜单集合
	 */
	public static void wrapZtreeData(List<Integer> currentMenuIds, List<Menu> menus){
		for (int i = 0; i < currentMenuIds.size(); i++) {
			for (int j = 0; j < menus.size(); j++) {
				if(currentMenuIds.get(i).intValue()==menus.get(j).getId().intValue()){
					menus.get(j).setChecked(true);
					break;
				}
			}
		}
	}
	
}
