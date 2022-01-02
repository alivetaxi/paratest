package com.taiwanlife.paratest.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.taiwanlife.paratest.domain.entity.SysUser;

@Mapper
public interface SysUserMapper {

	@Select("select * from sys_user where id = #{id}")
	public SysUser getById(String id);

}
