package com.krt.spring.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.krt.spring.mybatis.entity.Goods;
import com.krt.spring.mybatis.entity.PageTableForm;

public interface GoodsMapper {
	public List<Goods> queryGoods(@Param("pageTableForm") PageTableForm pageTableForm);
    public int getCount();
    
    public int insert(Goods record);
	public int updateByPrimaryKey(Goods record);
	public Goods selectByPrimaryKey(Integer id);
	public int deleteByPrimaryKey(Integer id);
}
