package com.example.chpater07.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;

        String requestId = httpRequest.getHeader("Request-Id");

        log.info("Successfully authenticated request with id = {}", requestId);

        chain.doFilter(request, response);
    }
}
