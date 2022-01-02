package com.taiwanlife.paratest.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taiwanlife.paratest.domain.entity.Function;
import com.taiwanlife.paratest.domain.entity.SysUser;
import com.taiwanlife.paratest.service.SysUserService;

@Service("userDetailsService")
public class FinUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getUserById(username);

		if (null == sysUser) {
			throw new UsernameNotFoundException("請聯繫AP設定權限");
		}

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(sysUser.getRole()));
		SecurityUser securityUser = new SecurityUser(username, authorities);
		securityUser.setSysUser(sysUser);

		List<Function> functions = sysUserService.getFunctionByRole(sysUser.getRole());
		securityUser.setFunctions(functions);
		UserSession.setAttribute(UserSession.FIN_SESSION_USER, securityUser);
		return securityUser;
	}

}
