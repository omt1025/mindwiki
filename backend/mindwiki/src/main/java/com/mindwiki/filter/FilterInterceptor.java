//package com.mindwiki.filter;
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//
//@WebFilter(urlPatterns= "/**")
//public class FilterInterceptor implements Filter, WebMvcConfigurer {
//	
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		//log.info("init XSSFilter");
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//	
//		//log.info("##### filter - before #####");
//		
//		
//		res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
//        res.setHeader("Access-Control-Max-Age", "3600");
//        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//       // res.setHeader("Access-Control-Allow-Credentials", "true");
//        res.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin");
//        //res.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
//		chain.doFilter(req, res);
//		//log.info("##### filter - after #####");
//	}
//	
////	@Override
////	public void destroy() {
////		//log.info("destroy XSSFilter");
////	}
//
////	@Override
////	public void addCorsMappings(CorsRegistry registry) {
//////		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
////		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
//////		.allowCredentials(true);
//////				.exposedHeaders("auth-token");d
////	}
//	
//
//	
//}