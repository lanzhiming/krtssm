package com.krt.spring.mybatis.web;


import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SystemController {
	private static final Logger log=LoggerFactory.getLogger(SystemController.class);
	
	@RequestMapping(value="/sys/f")
	public String index(){
		return "sys/index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "sys/login";
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "sys/login";
	}
}

