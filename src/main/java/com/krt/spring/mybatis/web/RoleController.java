package com.krt.spring.mybatis.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krt.spring.mybatis.entity.Role;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.RoleService;
import com.krt.spring.mybatis.service.UserService;


/**
 * 角色的Controller.
 */
@Controller
@RequestMapping(value = "/role")
//@RequiresPermissions(value = { "sys:user:mgr" })
public class RoleController {

	@Autowired
	private RoleService roleService;
	
//	@RequiresPermissions(value = { "sys:user:view" })
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = roleService.getAllRole();
		model.addAttribute("roles", roles);

		return "sys/roleList";
	}
	
	
	
	
//	@RequestMapping(value = "delete/{id}")
//	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
//		User user = userService.getUser(id);
//		if(SecurityUtils.getSubject().getPrincipal().equals(user.getUsername())){
//			redirectAttributes.addFlashAttribute("message", "不能删掉当前登陆的用户" + user.getUsername());
//		}else{
//			userService.deleteUser(id);
//			redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUsername() + "成功");
//		}
//		
//		return "redirect:/user";
//	}
	
}
