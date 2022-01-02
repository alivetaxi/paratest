package com.taiwanlife.paratest.web.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.taiwanlife.paratest.domain.entity.Function;
import com.taiwanlife.paratest.web.security.SecurityUser;
import com.taiwanlife.paratest.web.security.UserSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestFilter implements Filter {

	private static final String[] IGNORED_PATTERNS = { "(.*).js", "(.*).map", "(.*).css", "(.*).ttf", "(.*).woff(.*)" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		SecurityUser securityUser = UserSession.getSecurityUser();
		if (null != securityUser && !Arrays.stream(IGNORED_PATTERNS).anyMatch(req.getRequestURI()::matches)) {
			log.info("{} is requesting to {}", securityUser.getUsername(), req.getRequestURI());
			if (Arrays.asList("/login", "/", "/index").contains(req.getRequestURI())) {
				UserSession.clearBreadcrumb();
			} else {
				Function currentFunction = securityUser.getUrlMap().getOrDefault(req.getRequestURI(), null);
				if (null != currentFunction) {
					securityUser.setCurrentModule(securityUser.getModuleMap().get(currentFunction.getParentId()));
				} else {
					throw new AccessDeniedException("請聯繫AP設定權限");
				}
				securityUser.setCurrentFunction(currentFunction);
			}
		}
		chain.doFilter(request, response);
	}

}