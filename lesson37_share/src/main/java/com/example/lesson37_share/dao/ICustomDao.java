package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ssm.entity.Custom;

@Repository("customDao")
public interface ICustomDao {

	@Select("select * from custom limit #{0},#{1}")
	List<Custom> queryCustoms(int start, int rows);

	@Select("select count(0) from custom")
	int queryCustomCount();

	@Insert("insert into custom value(null,#{custom.name},#{custom.age},#{custom.phone},#{custom.email})")
	int addCustom(@Param("custom") Custom custom);

	@Delete("delete from custom where id=#{0}")
	int deleteCustom(int id);

	@Select("select * from custom where id=#{0}")
	Custom queryCustom(int id);

	@Update("update custom set name=#{c.name},age=#{c.age},phone=#{c.phone},email=#{c.email} where id=#{c.id}")
	int updateCustom(@Param("c") Custom custom);

}
