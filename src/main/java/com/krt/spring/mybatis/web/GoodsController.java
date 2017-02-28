package com.krt.spring.mybatis.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krt.spring.mybatis.entity.Goods;
import com.krt.spring.mybatis.entity.PageTableForm;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.GoodsService;

@Controller
@RequestMapping("/goodsManage")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/init")
    public String init(Model model) {
        return "redirect:/goodsManage/query";
    }
	
	@RequestMapping(value="/query")
    public String queryUserInfo(Model model,HttpServletRequest request, PageTableForm pageTableForm) {
        String name = request.getParameter("name")!=null ? request.getParameter("name") : null;
        if(name!=null){
            pageTableForm.setName(name);
        }
        pageTableForm = goodsService.queryGoods(pageTableForm);
        model.addAttribute("pageTableForm", pageTableForm);
        return "sys/goodsList";
    }
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("goods", new Goods());
		model.addAttribute("action", "create");
		return "sys/goodsForm";
	}
	
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Goods goods, RedirectAttributes redirectAttributes) {
		goodsService.updateGoods(goods);
		redirectAttributes.addFlashAttribute("message", "创建商品成功");
		return "redirect:/goodsManage/query";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Goods goods = goodsService.getGoods(id);
		model.addAttribute("goods", goods);
		model.addAttribute("action", "update");
		return "sys/goodsForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("goods") Goods goods, RedirectAttributes redirectAttributes) {
		goodsService.updateGoods(goods);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/goodsManage/query";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		goodsService.deleteGoods(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/goodsManage/query";
	}
}
