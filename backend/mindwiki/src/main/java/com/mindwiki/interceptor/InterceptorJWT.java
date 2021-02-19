/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 인터셉터 jwt확인
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mindwiki.service.JwtService;

@Component
public class InterceptorJWT implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(InterceptorJWT.class);

	private static final String HEADER_AUTH = "jwt";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String token = request.getParameter("jwt");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		
		if (token != null && jwtService.verifyJWT(token) != null) {
			logger.info("토큰 사용 가능 ", token);
			return true;
		} else {
			logger.info("토큰 사용 불가능 ", token);
			response.getWriter().write("{\"message\":\"FAIL\"}");
			}
		return false;

	}
}
