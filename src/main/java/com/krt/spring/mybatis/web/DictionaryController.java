package com.krt.spring.mybatis.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krt.spring.mybatis.entity.Dictionary;
import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.DictionaryService;
import com.krt.spring.mybatis.service.DictionaryTypeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dictionary")
public class DictionaryController{

    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 字典管理页
     *
     * @return
     */
//    @RequiresPermissions("dictionaryType:list")
    @RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Dictionary> dictionarys = dictionaryService.getAlldictionary();
		model.addAttribute("dictionarys", dictionarys);
		return "sys/dictionaryList";
	}
    
    @RequestMapping(value = "addKeyValue/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
    	DictionaryType dictionaryType=dictionaryTypeService.selectByPrimaryKey(id);
		model.addAttribute("dictionaryType", dictionaryType);
		return "sys/dictionaryForm";
	}
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name,
			@RequestParam(value = "type") String type,@RequestParam(value = "pid") String pid,
			@RequestParam(value = "code") String code,@RequestParam(value = "remark") String remark) {
    	Dictionary dictionary=null;
		if(null==id||id.equals("")){
			dictionary=new Dictionary();
			dictionary.setName(name);
			dictionary.setType(type);
			dictionary.setPid(Integer.valueOf(pid));
			dictionary.setCode(code);
			dictionary.setRemark(remark);
		}else{
			dictionary=dictionaryService.selectByPrimaryKey(Integer.valueOf(id));
		}
		dictionaryService.updateDictionary(dictionary);
		return "redirect:/dictionary";
	}


}
