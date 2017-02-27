package com.krt.spring.mybatis.common.realm;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.krt.spring.mybatis.entity.Menu;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.UserService;


@Component
public class SystemAuthorizingRealm extends AuthorizingRealm{
	
	@Autowired
	UserService userService;
	/*
	 * 授权方法
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Object principal=principals.getPrimaryPrincipal();
//		userService.updateLastLoginDate(principal.toString());
		User user = userService.findUserByUsername(principal.toString());
		List<Menu> list = userService.getCurrentMenu(user.getId());
		/*if("superadmin".equals(principal)){
			info.addRole("admin");
			info.addStringPermission("sys:insert");
		}else{
			
		}
		info.addRole("user");*/
		try {
			for (Menu menu : list){
				if (!StringUtils.isEmpty(menu.getPermission())){
					// 添加基于Permission的权限信息
					//for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(menu.getPermission());
					//}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 添加用户权限
		//info.addStringPermission("user");
		// 添加用户角色信息
		/*for (Role role : user.getRoleList()){
			info.addRole(role.getEnname());
		}*/
		info.addRole("user");
		return info;
	}
	
	/*
	 * 认证方法
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		User user=userService.findUserByUsername(token.getPrincipal().toString());
		Object principal=token.getPrincipal();
		char[] credentials = (char[]) token.getCredentials();
		//String credentialsString = new String(credentials);
		String salt=user.getSalt();
		//认证信息
		Object hashedCredentials=user.getPassword();//"f95abf3936f71d3f67e1cfb7f33ba238";
		//设置盐值
		String source="krt"+principal+new String(credentials)+salt;
		ByteSource credentialsSalt=new Md5Hash(source);
		//当前realm的name
		String realmName=getName();
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}
	
	/*
	 * 设定密码校验
	 */
	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		setCredentialsMatcher(credentialsMatcher);
	}
	
	//生成数据库密码
	@Test
	public void test1(){
		String algorithmName="MD5";
		String username="superadmin";
		String credentials ="admin";//密码
		String saltsource=UUID.randomUUID().toString();//数据库salt值
		System.out.println(saltsource);//数据库密码
		Object salt=new Md5Hash("krt"+username+credentials+saltsource);
		int hashIterations=1024;
		Object result=new SimpleHash(algorithmName,credentials, salt, hashIterations);
		System.out.println(result.toString());//数据库密码
	}

}
