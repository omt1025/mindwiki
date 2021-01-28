package com.mindwiki.controller;


//세션을 쿠키로 관리한다.
//https://lts0606.tistory.com/251 이론정리


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

        // This will set to use COOKIE only
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