package com.i8m.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class DomianNameUtil {

	public String getDomainName() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String server = request.getHeader("x-forwarded-host");
		if (server != null) {
			server = request.getScheme() + "://" + server;
		} else {
			if (!"/".equals(request.getRequestURI())) {
				server = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			} else {
				server = request.getRequestURL().toString()
						.substring(0, request.getRequestURL().toString().length() - 1);
			}
		}

		return server;

	}

}
