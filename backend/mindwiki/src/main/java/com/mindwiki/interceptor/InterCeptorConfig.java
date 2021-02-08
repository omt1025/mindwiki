package com.mindwiki.interceptor;

/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : InterCeptor 관리
* 최종 수정일: 2021.02.04.
*******************************************************************************/
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class InterCeptorConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS = { "/" , "/mindwiki/GoogleOAuth",
			"/mindwiki/login","/mindwiki/oauth","/mindwiki/profile/register","/mindwiki/image","/mindwiki/image/**"
			
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
