package com.teja.task_tracker_api.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.io.IOException;

@Configuration
public class DebugFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> logRequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest req = (HttpServletRequest) request;
                System.out.println("➡️ Incoming request: " + req.getMethod() + " " + req.getRequestURI());
                chain.doFilter(request, response);
            }
        });
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
