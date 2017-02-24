package com.hf.test;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context-mybatis.xml"})
@TestPropertySource({"classpath:application.properties"})
public class MybatisTest{
	
	@Autowired
	private DataSource dataSource;
	
	
	
	@Test
	public void test1() throws SQLException{
		dataSource.getConnection();
	}

}

