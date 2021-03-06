package com.krt.spring.mybatis.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krt.spring.mybatis.entity.Dictionary;
import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.Role;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.service.DictionaryTypeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dictionaryType")
public class DictionaryTypeController{

    @Resource
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 字典类型管理页
     *
     * @return
     */
//    @RequiresPermissions("dictionaryType:list")
    @RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<DictionaryType> dictionaryTypes = dictionaryTypeService.getAlldictionaryType();
		model.addAttribute("dictionaryTypes", dictionaryTypes);

		return "sys/dictionaryTypeList";
	}
    
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("dictionaryType", new DictionaryType());
		model.addAttribute("action", "create");
		return "sys/dictionaryTypeForm";
	}
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
	public String add(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name,
			@RequestParam(value = "code") String code,@RequestParam(value = "remark") String remark) {
    	DictionaryType dictionaryType=null;
		if(null==id||id.equals("")){
			dictionaryType=new DictionaryType();
			dictionaryType.setName(name);
			dictionaryType.setCode(code);
			dictionaryType.setRemark(remark);
		}else{
			dictionaryType=dictionaryTypeService.selectByPrimaryKey(Integer.valueOf(id));
		}
		dictionaryTypeService.updateDictionaryType(dictionaryType);
		return "redirect:/dictionaryType";
	}
    
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
    	DictionaryType dictionaryType = dictionaryTypeService.selectByPrimaryKey(id);
		model.addAttribute("dictionaryType", dictionaryType);
		model.addAttribute("action", "update");
		return "sys/dictionaryTypeForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("dictionaryType") DictionaryType dictionaryType, RedirectAttributes redirectAttributes) {
		
		dictionaryTypeService.updateDictionaryType(dictionaryType);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/dictionaryType";
	}
	
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		DictionaryType dictionaryType = dictionaryTypeService.selectByPrimaryKey(id);
		try {
			dictionaryTypeService.delete(id, dictionaryType.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("message", "删除成功");
	
		return "redirect:/dictionaryType";
	}

    /**
     * 字典类型管理
     *
     * @param start
     * @param length
     * @param draw
     * @param request
     * @return
     */
//    @RequiresPermissions("dictionaryType:list")
//    @RequestMapping("admin/system/dictionaryType/dictionaryType_list")
//    @ResponseBody
//    public DataTable dictionaryType_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
//        String code = Common.toUTF(request.getParameter("code"));
//        String name = Common.toUTF(request.getParameter("name"));
//        Map para = new HashMap();
//        para.put("code", code);
//        para.put("name", name);
//        DataTable dt = dictionaryTypeService.selectListPara(start, length, draw, para);
//        return dt;
//    }
//
//    /**
//     * 添加字典类型
//     *
//     * @param dictionaryType 字典类型实体
//     * @return
//     */
//    @LogAop(description = "添加字典类型")
//    @RequiresPermissions("dictionaryType:insert")
//    @RequestMapping("admin/system/dictionaryType/dictionaryType_insert")
//    @ResponseBody
//    public ReturnBean dictionaryType_insert(DictionaryType dictionaryType) {
//        ReturnBean rb;
//        try {
//            // 检测字典代码是否重复
//            Integer count = dictionaryTypeService.checkCode(dictionaryType.getCode(), dictionaryType.getId());
//            if (count == 0) {
//                dictionaryTypeService.insert(dictionaryType);
//                rb = ReturnBean.getSuccessReturnBean();
//            } else {
//                rb = ReturnBean.getCustomReturnBean("error_code");
//            }
//        } catch (Exception e) {
//            rb = ReturnBean.getErrorReturnBean();
//            logger.error("添加字典类型失败", e);
//        }
//        return rb;
//    }
//
//    /**
//     * 修改字典类型
//     *
//     * @param dictionaryType 字典类型实体
//     * @return
//     */
//    @LogAop(description = "修改字典类型")
//    @RequiresPermissions("dictionaryType:update")
//    @RequestMapping("admin/system/dictionaryType/dictionaryType_update")
//    @ResponseBody
//    public ReturnBean dictionaryType_update(DictionaryType dictionaryType) {
//        ReturnBean rb;
//        try {
//            // 检测字典代码是否重复
//            Integer count = dictionaryTypeService.checkCode(dictionaryType.getCode(), dictionaryType.getId());
//            if (count == 0) {
//                dictionaryTypeService.update(dictionaryType);
//                rb = ReturnBean.getSuccessReturnBean();
//            } else {
//                rb = ReturnBean.getCustomReturnBean("error_code");
//            }
//        } catch (Exception e) {
//            rb = ReturnBean.getErrorReturnBean();
//            logger.error("修改字典类型失败", e);
//        }
//        return rb;
//    }
//
//    /**
//     * 删除字典类型
//     *
//     * @param id   类型id
//     * @param code 类型编码
//     * @return
//     */
//    @LogAop(description = "删除字典类型")
//    @RequiresPermissions("dictionaryType:delete")
//    @RequestMapping("admin/system/dictionaryType/dictionaryType_delete")
//    @ResponseBody
//    public ReturnBean dictionaryType_delete(Integer id, String code) {
//        ReturnBean rb;
//        try {
//            dictionaryTypeService.delete(id, code);
//            rb = ReturnBean.getSuccessReturnBean();
//        } catch (Exception e) {
//            rb = ReturnBean.getErrorReturnBean();
//            logger.error("删除字典类型失败", e);
//        }
//        return rb;
//    }

}
