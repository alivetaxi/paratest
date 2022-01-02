package com.taiwanlife.paratest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiwanlife.paratest.domain.entity.Function;
import com.taiwanlife.paratest.domain.entity.SysUser;
import com.taiwanlife.paratest.service.SysUserService;
import com.taiwanlife.paratest.service.mapper.FunctionMapper;
import com.taiwanlife.paratest.service.mapper.SysUserMapper;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private FunctionMapper functionMapper;

	@Override
	public SysUser getUserById(String id) {
		SysUser sysUser = sysUserMapper.getById(id);
		if (null == sysUser || StringUtils.isBlank(sysUser.getId())) {
			return null;
		} else {
			return sysUser;
		}
	}

	@Override
	public List<Function> getFunctionByRole(String role) {
		return functionMapper.getByRole(role);
	}

}
