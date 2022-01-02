package com.taiwanlife.paratest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.taiwanlife.paratest.domain.entity.Function;

@Mapper
public interface FunctionMapper {

	@Select("select * from function where id in "
			+ "(select function_id from role_function where role = #{role}) order by parent_id")
	public List<Function> getByRole(String role);

}
