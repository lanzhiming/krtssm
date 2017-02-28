package com.krt.spring.mybatis.service;

import org.springframework.stereotype.Service;

import com.krt.spring.mybatis.entity.Dictionary;
import com.krt.spring.mybatis.entity.DictionaryType;
import com.krt.spring.mybatis.entity.User;
import com.krt.spring.mybatis.mapper.DictionaryMapper;
import com.krt.spring.mybatis.mapper.DictionaryTypeMapper;

import java.util.List;

import javax.annotation.Resource;

@Service
public class DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    
    public Dictionary selectByPrimaryKey(Integer id){
    	return dictionaryMapper.selectByPrimaryKey(id);
    }
    
    public List<Dictionary> getAlldictionary(){
		
		return dictionaryMapper.findAll();
	}

    
    public void updateDictionary(Dictionary dictionary) {
		if(dictionary.getId()==null){
			dictionaryMapper.insert(dictionary);
		}else{
			dictionaryMapper.update(dictionary);
		}
	}
//    /**
//     * 删除字典
//     *
//     * @param id
//     * @param code
//     * @throws Exception
//     */
//    public void delete(Integer id, String code) throws Exception {
//        dictionaryMapper.deleteByPrimaryKey(id);
//        dictionaryMapper.deleteByType(code);
//    }
//
//    /**
//     * 检测字典编码
//     *
//     * @param code
//     * @param id
//     * @return
//     */
//    public Integer checkCode(String code, Integer id) {
//        return dictionaryTypeMapper.checkCode(code, id);
//    }

}
