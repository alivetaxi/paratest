package com.taiwanlife.paratest.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.taiwanlife.paratest.domain.entity.Function;
import com.taiwanlife.paratest.domain.entity.SysUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;

	public SecurityUser(String username, Collection<? extends GrantedAuthority> authorities) {
		super(username, "", true, true, true, true, authorities);
	}

	private String sessionId;
	private SysUser sysUser;
	private Map<String, List<Function>> funcMap;
	private Map<String, Function> urlMap;
	private Map<Integer, Function> moduleMap;
	private Function currentFunction;
	private Function currentModule;

	public void setFunctions(List<Function> functions) {
		Map<String, List<Function>> funcMap = new TreeMap<String, List<Function>>();
		Map<String, Function> urlMap = new TreeMap<String, Function>();
		Map<Integer, Function> moduleMap = new TreeMap<Integer, Function>();
		for (Function f : functions) {
			if (0 == f.getParentId()) {
				funcMap.put(f.getName(), new ArrayList<>());
				moduleMap.put(f.getId(), f);
			} else {
				funcMap.get(moduleMap.get(f.getParentId()).getName()).add(f);
				urlMap.put(f.getUrl(), f);
			}
		}
		this.funcMap = funcMap;
		this.urlMap = urlMap;
		this.moduleMap = moduleMap;
	}
}
