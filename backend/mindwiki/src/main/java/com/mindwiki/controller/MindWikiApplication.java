/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : mindwiki
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@ServletComponentScan//cors를 위해 추가1.
@ComponentScan("com.mindwiki.*")
@SpringBootApplication
public class MindWikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindWikiApplication.class, args);
	}

	
}
