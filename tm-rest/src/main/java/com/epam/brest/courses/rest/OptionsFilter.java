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

    /**
     *
     * @param filterConfig filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public final void doFilter(final ServletRequest servletRequest,
                               final ServletResponse servletResponse,
                               final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServRequest = (HttpServletRequest)
                servletRequest;
        HttpServletResponse httpServResponse = (HttpServletResponse)
                servletResponse;

        if (httpServResponse.getHeader(
                "Access-Control-Allow-Origin") == null) {

            httpServResponse.addHeader("Access-Control-Allow-Origin",
                    httpServRequest.getHeader("Origin"));

        }

        httpServResponse.addHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        httpServResponse.addHeader("Access-Control-Allow-Headers",
                "Content-Type");

        if (HttpMethod.OPTIONS.toString().equals(
                httpServRequest.getMethod())) {

            httpServResponse.addHeader(
                    "Allow", "GET, POST, PUT, DELETE, OPTIONS");

        } else {

            filterChain.doFilter(servletRequest, servletResponse);

        }

    }

    /**
     *
     */
    @Override
    public void destroy() {

    }

}
