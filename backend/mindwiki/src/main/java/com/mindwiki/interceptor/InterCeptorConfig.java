/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 인터셉터설정
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.interceptor;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class InterCeptorConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS = { "/" , "/mindwiki/GoogleOAuth",
			"/mindwiki/login","/mindwiki/oauth","/mindwiki/profile/register","/mindwiki/image","/mindwiki/image/**"
			,"/mindwiki/mind/comment/read/{no}","/mindwiki/liveRanking/list","/mindwiki/profile/sendTempPassword",
			"mindwiki/profile/image/", "mindwiki/profile/image/**", "/mindwiki/profile/getProfile"
			
			};
	
	@Autowired
	private InterceptorJWT jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
				.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
		

	}
	

 
}
