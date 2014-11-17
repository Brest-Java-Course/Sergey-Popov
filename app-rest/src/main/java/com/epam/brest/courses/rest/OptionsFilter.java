package com.epam.brest.courses.rest;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by beast on 17.11.14. At 11.33
 */
public class OptionsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletResponse.getHeader("Access-Control-Allow-Origin") == null) {
            httpServletResponse.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        }

        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");

        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.addHeader("Allow", "GET, POST, PUT, DELETE, OPTIONS");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}