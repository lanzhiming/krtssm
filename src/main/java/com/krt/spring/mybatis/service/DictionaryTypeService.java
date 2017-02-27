package com.krt.spring.mybatis.service;

import org.springframework.stereotype.Service;

import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.mapper.DictionaryMapper;
import com.krt.spring.mybatis.mapper.DictionaryTypeMapper;

import java.util.List;

import javax.annotation.Resource;

@Service
public class DictionaryTypeService {

    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    
    public List<DictionaryType> getAlldictionaryType(){
		
		return dictionaryTypeMapper.findAll();
	}

    /**
     * 删除字典
     *
     * @param id
     * @param code
     * @throws Exception
     */
    public void delete(Integer id, String code) throws Exception {
        dictionaryTypeMapper.deleteByPrimaryKey(id);
        dictionaryMapper.deleteByType(code);
    }

    /**
     * 检测字典编码
     *
     * @param code
     * @param id
     * @return
     */
    public Integer checkCode(String code, Integer id) {
        return dictionaryTypeMapper.checkCode(code, id);
    }

}
