package com.krt.spring.mybatis.entity;

import com.krt.spring.mybatis.entity.base.DataEntity;

public class Dictionary extends DataEntity {

    private String type;
    private Integer pid;
    private String code;
    private String name;
    private String remark;

   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

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
		return "Dictionary [type=" + type + ", pid=" + pid + ", code=" + code + ", name=" + name + ", remark=" + remark + "]";
	}
    
    
}