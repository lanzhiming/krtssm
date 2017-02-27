package com.krt.spring.mybatis.entity;

import com.krt.spring.mybatis.entity.base.DataEntity;

public class DictionaryType extends DataEntity {
	
	private String code;
	private String name;
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public String toString() {
		return "DictionaryType [code=" + code + ", name=" + name + ", remark=" + remark + "]";
	}
	
	
}