package com.tzyangtang.rest.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GeneralInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(GeneralInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURI() + " user:" + request.getRemoteUser());

		logger.info(sb.toString());

		return super.preHandle(request, response, handler);
	}
}
