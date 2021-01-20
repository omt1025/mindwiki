package com.mindwiki.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.mindwiki.*")
@SpringBootApplication
public class MindWikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindWikiApplication.class, args);
	}

}
