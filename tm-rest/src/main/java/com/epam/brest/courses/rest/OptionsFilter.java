package com.epam.brest.courses.rest;

import org.springframework.http.HttpMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by beast on 22.11.14. At 12.56
 */
public class OptionsFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public final void doFilter(final ServletRequest servletRequest,
                               final ServletResponse servletResponse,
                               final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)
                servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)
                servletResponse;

        if (httpServletResponse.getHeader(
                "Access-Control-Allow-Origin") == null) {

            httpServletResponse.addHeader("Access-Control-Allow-Origin",
                    httpServletRequest.getHeader("Origin"));

        }

        httpServletResponse.addHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.addHeader("Access-Control-Allow-Headers",
                "Content-Type");

        if (HttpMethod.OPTIONS.toString().equals(
                httpServletRequest.getMethod())) {

            httpServletResponse.addHeader(
                    "Allow", "GET, POST, PUT, DELETE, OPTIONS");

        } else {

            filterChain.doFilter(servletRequest, servletResponse);

        }

    }

    @Override
    public void destroy() {

    }

}
