package com.mindwiki.controller;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 세션 ID를 쿠키로 전환
* 최종 수정일: 2021.02.04.
*******************************************************************************/

//세션을 쿠키로 관리한다.

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
     
        super.onStartup(servletContext);

        servletContext
            .setSessionTrackingModes(
                Collections.singleton(SessionTrackingMode.COOKIE)
        );
     
        SessionCookieConfig sessionCookieConfig=
                servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MindWikiApplication.class);
    }

}