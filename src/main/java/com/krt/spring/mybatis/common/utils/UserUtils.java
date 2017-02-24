package com.krt.spring.mybatis.common.utils;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.UserService;


public class UserUtils {
	
	public static final String USER_CACHE = "userCache";
	public static final String SYS_CACHE = "sysCache";
	public static final String USER_CACHE_ID_ = "id_";
	
	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_N = "menuForN";
	public static final String CACHE_MENU_LIST_R = "menuListForR";
	
	public static Cache getSpringCache(String cacheName){
		CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
		return cacheManager.getCache(cacheName);
	}
	
	/**
	 * 加密密码
	 * @param user （传入的password是明文）
	 */
	public static void encryptPassword(User user){
     	String algorithmName="MD5";
		String username=user.getUsername();
		String credentials =user.getPassword();//原始密码
		String saltsource=UUID.randomUUID().toString();//数据库salt值
		Object salt=new Md5Hash("Hf++cllove"+username+credentials+saltsource);
		int hashIterations=1024;
		Object result=new SimpleHash(algorithmName,credentials, salt, hashIterations);
		//System.out.println(result.toString());//数据库密码
		user.setSalt(saltsource);
		user.setPassword(result.toString());
	}
	
	/**
	 * 获取当前用户授权菜单(用于导航栏)
	 * @return
	 */
//	public static Menu getMenuForN(){
//		Menu menu = (Menu)getCache(CACHE_MENU_N);
//		if (menu == null){
//			UserService userService = SpringContextHolder.getBean(UserService.class);
//			//User user = userService.findUserByUsername(getPrincipal().toString());
//			List<Menu> menus = userService.getCurrentMenu(getUser().getId());
//			List<Menu> menusForN = MenuTree.wrapMenuForNavigation(menus);
//			MenuTree menuTree = new MenuTree(menusForN);
//			menu = menuTree.recursiveTree(1);//为导航栏构建数结构
//			menuTree.sortMenuForNavigation(menu);
//			putCache(CACHE_MENU_N, menu);
//		}
//		return menu;
//	}
	
	public static User getUser(){
		User user = (User) getCache(USER_CACHE_ID_+getPrincipal());
		if (user == null){
			UserService userService = SpringContextHolder.getBean(UserService.class);
			user = userService.findUserByUsername(getPrincipal().toString());
			putCache(USER_CACHE_ID_+getPrincipal(), user);
		}
		return user;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Object getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Object principal = subject.getPrincipal();
			if (principal != null){
				return principal;
			}
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}
}
