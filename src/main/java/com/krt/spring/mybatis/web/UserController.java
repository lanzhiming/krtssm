package com.krt.spring.mybatis.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.UserService;


/**
 * 用户的Controller.
 */
@Controller
@RequestMapping(value = "/user")
@RequiresPermissions(value = { "sys:user:mgr" })
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequiresPermissions(value = { "sys:user:view" })
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<User> users = userService.getAllUser();
		model.addAttribute("users", users);

		return "sys/userList";
	}
	
	@RequiresPermissions(value = { "sys:user:edit" })
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.getUser(id);
		user.setPassword(null);
		model.addAttribute("user", user);
		return "sys/userForm";
	}
	
	/**
	 * Ajax请求。
	 */
	@RequestMapping(value = "updateUser")
	@ResponseBody
	public String updateUser(HttpServletRequest request) {
		JsonObject obj=new JsonObject();
		try {
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userService.getUser(Integer.valueOf(id));
			user.setUsername(username);
			updatePassword(username, password, user);
			userService.updateUser(user);
			obj.addProperty("msg", "success");
			obj.addProperty("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			obj.addProperty("msg", "fail");
			obj.addProperty("code", "1");
		}
		return obj.toString();
	}
	
	@RequiresPermissions(value = { "sys:user:add" })
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "id") String id,@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) {
		User user=null;
		if(null==id||id.equals("")){
			user=new User();
		}else{
			user = userService.getUser(Integer.valueOf(id));
		}
		user.setUsername(username);
		updatePassword(username, password, user);
		userService.updateUser(user);
		return "redirect:/user";
	}

	private void updatePassword(String username, String password, User user) {
		if(StringUtils.isNotBlank(password)){
			String algorithmName="MD5";
			String saltsource=UUID.randomUUID().toString();//数据库salt值
			Object salt=new Md5Hash("krt"+username+password+saltsource);
			int hashIterations=1024;
			Object result=new SimpleHash(algorithmName,password, salt, hashIterations);
			user.setSalt(saltsource);
			user.setPassword(result.toString());
		}
	}
	
	@RequiresPermissions(value = { "sys:user:delete" })
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		User user = userService.getUser(id);
		if(SecurityUtils.getSubject().getPrincipal().equals(user.getUsername())){
			redirectAttributes.addFlashAttribute("message", "不能删掉当前登陆的用户" + user.getUsername());
		}else{
			userService.deleteUser(id);
			redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUsername() + "成功");
		}
		
		return "redirect:/user";
	}
	
	@RequiresPermissions(value = { "sys:user:edit" })
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		User dbUser = userService.getUser(user.getId());
		dbUser.setUsername(user.getUsername());
		updatePassword(user.getUsername(), user.getPassword(), dbUser);
		userService.updateUser(dbUser);
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getUsername() + "成功");
		return "redirect:/user";
	}
	

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.getUser(id));
		}
	}
}
