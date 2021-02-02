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
