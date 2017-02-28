package com.krt.spring.mybatis.entity;

import com.krt.spring.mybatis.entity.base.DataEntity;

/**
 * 商品
 * @author krt
 *
 */
public class Goods extends DataEntity{
    private Integer cateId;  
    private String name;  
    private double price;  
    private String description;  
    private Integer orderNo;
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
    

}
