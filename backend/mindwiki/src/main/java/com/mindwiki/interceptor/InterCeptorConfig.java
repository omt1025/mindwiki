package com.mindwiki.interceptor;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class InterCeptorConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS = { "/" , "/mindwiki/GoogleOAuth",
			"/mindwiki/login","/mindwiki/oauth","/profile/register","/mindwiki/image","/mindwiki/image/**"
			
			};
	
	
	@Autowired
	private InterceptorJWT jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
				.excludePathPatterns(EXCLUDE_PATHS);

	}

	
//  Interceptor

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
		

	}
	

 
}
