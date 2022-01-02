package com.taiwanlife.paratest.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserSession {

	public static final String FIN_SESSION_USER = "_finSessionUser";

	public static void setAttribute(String name, Object value) {
		getCurrentAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(String name) {
		return (null == getCurrentAttributes()) ? null
				: (T) getCurrentAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	public static void removeAttribute(String name) {
		getCurrentAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	public static String getId() {
		return getCurrentAttributes().getSessionId();
	}

	public static String getUserSessionId() {
		if (null == getSecurityUser())
			return getSecurityUser().getSessionId();
		return null;
	}

	private static ServletRequestAttributes getCurrentAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return (null == attr) ? null : attr.getResponse();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return (null == attr) ? null : attr.getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static boolean hasPrincipal() {
		return (null == SecurityContextHolder.getContext().getAuthentication() ? false : true);
	}

	public static SecurityUser getSecurityUser() {
		if (null == getAttribute(FIN_SESSION_USER) && null != SecurityContextHolder.getContext().getAuthentication()) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecurityUser) {
				return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
			return null;
		} else if (null != getAttribute(FIN_SESSION_USER)) {
			return (SecurityUser) getAttribute(FIN_SESSION_USER);
		}
		return null;
	}

	public static void clearBreadcrumb() {
		SecurityUser securityUser = getSecurityUser();
		if (null != securityUser) {
			securityUser.setCurrentModule(null);
			securityUser.setCurrentFunction(null);
		}
	}

}
