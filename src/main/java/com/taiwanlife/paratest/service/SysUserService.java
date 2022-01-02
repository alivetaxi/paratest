package com.taiwanlife.paratest.service;

import java.util.List;

import com.taiwanlife.paratest.domain.entity.Function;
import com.taiwanlife.paratest.domain.entity.SysUser;

public interface SysUserService {

	public SysUser getUserById(String id);

	public List<Function> getFunctionByRole(String role);

}
