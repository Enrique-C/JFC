/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.token.JsonWebToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Manages Token filters.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Juan Martinez.
 */
@WebFilter(urlPatterns = {"/*"})
public class TokenFilter implements Filter {

    /**
     * Initializes filter configuration.
     * @param filterConfig value.
     * @throws ServletException if something was wrong.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Allows to validate token.
     * @param request client value
     * @param response value for the client.
     * @param chain value.
     * @throws IOException when something was wrong.
     * @throws ServletException if servlet was corrupted.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        System.out.println("filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        String auth = req.getHeader("Authorization");
        JsonWebToken jwt = new JsonWebToken();

        if (auth != null) {
            if (jwt.validateToken(auth)) {
                chain.doFilter(request, response);
            }
        }

        if (url.contains("/login") || url.contains("/extractMd5") || url.contains("/webjars") ||
                url.contains("/swagger-resources") || url.contains("/v2/api-docs") || url.contains("/swagger-ui.html")) {
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "access denied");
        }
    }

    /**
     * Destroys objects.
     */
    @Override
    public void destroy() {

    }
}
