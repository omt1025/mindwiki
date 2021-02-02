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
public class InterceptorJWT implements HandlerInterceptor{
	
	public static final Logger logger = LoggerFactory.getLogger(InterceptorJWT.class);
	
	private static final String HEADER_AUTH = "jwt";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		final String token = request.getParameter("jwt");
		
		
		System.out.println("토큰값 넘어오는지 "+token);
		if(token != null && jwtService.verifyJWT(token)!=null){
			logger.info("토큰 사용 가능 : {}", token);
			return true;
		}else{
			logger.info("토큰 사용 불가능 : {}", token);
			System.out.println("문제있음");
		}
		return false;

	}
}
