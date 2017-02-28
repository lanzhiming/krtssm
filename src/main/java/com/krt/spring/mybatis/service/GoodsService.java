package com.krt.spring.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krt.spring.mybatis.entity.Goods;
import com.krt.spring.mybatis.entity.PageTableForm;
import com.krt.spring.mybatis.entity.Role;
import com.krt.spring.mybatis.mapper.GoodsMapper;

@Service
public class GoodsService {
	@Autowired
    private GoodsMapper goodsMapper;
	
	public PageTableForm queryGoods(PageTableForm pageTableForm) {
        List<Goods> list = goodsMapper.queryGoods(pageTableForm);
        pageTableForm.setGoodsList(list);
        return pageTableForm;
    }
	
	public void updateGoods(Goods goods) {
		if(goods.getId()==null){
			goodsMapper.insert(goods);
		}else{
			goodsMapper.updateByPrimaryKey(goods);
		}
	}
	
	public Goods getGoods(Integer id){
		return goodsMapper.selectByPrimaryKey(id);
	}
	
	public void deleteGoods(Integer id) {
		goodsMapper.deleteByPrimaryKey(id);
	}
}
