package com.krt.spring.mybatis.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krt.spring.mybatis.common.utils.SysUtils;
import com.krt.spring.mybatis.common.utils.UserUtils;
import com.krt.spring.mybatis.entity.Menu;
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
	@Autowired UserService userService;
	
//	@RequiresPermissions(value = { "sys:user:view" })
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = roleService.getAllRole();
		model.addAttribute("roles", roles);

		return "sys/roleList";
	}
	
	@RequestMapping(value = "allocateduser/{id}")
	public String allocatedUser(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes) {
		Role role = roleService.getRole(id);
		//List<Position> list = positionService.getPositionPage(null, new HashMap<String, Object>(), 1, Integer.MAX_VALUE, "auto").getContent();
		List<User> users = userService.getAllUser();
		List<Integer> list = new ArrayList<Integer>();
		list.add(id);
		List<User> alreadyAllocatedUsers=roleService.getUsersByRoleId(list);
		model.addAttribute("role", role);
		model.addAttribute("users", users);
		model.addAttribute("alreadyAllocatedUsers", alreadyAllocatedUsers);
		return "sys/roleAllocated";
	}
	
	@RequestMapping(value = "allocatedusersave",method=RequestMethod.POST)
	@ResponseBody
	public boolean allocatedUserSave(@RequestParam("roleId")Long roleId,@RequestParam(value="userids[]",required=false)Long[] userids,Model model, RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
//			roleService.allocatedusersave(roleId, userids);
			roleService.saveAssignUser(roleId, userids);
			UserUtils.removeCache(UserUtils.CACHE_MENU_N);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@RequestMapping(value = "allocatedmenu/{id}")
	public String allocatedMenu(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);
		return "sys/roleAllocatedMenu";
	}
	
	@RequestMapping(value = "allocatedmenusave",method=RequestMethod.POST)
	@ResponseBody
	public boolean allocatedMenuSave(@RequestParam("roleId")Long roleId,@RequestParam(value="menuids[]",required=false)Long[] menuids,Model model, RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			roleService.allocatedmenusave(roleId, menuids);
			UserUtils.removeCache(UserUtils.CACHE_MENU_N);
			SysUtils.getSpringCache(SysUtils.SYS_CACHE).evict(SysUtils.CACHE_MENU_LIST_R+roleId);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@RequestMapping(value="/menu/get/{roleid}", method = RequestMethod.GET)
	@ResponseBody
	public  List<Menu> getMenu(@PathVariable("roleid") Integer id) {
		return SysUtils.getMenuListForR(id);
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
