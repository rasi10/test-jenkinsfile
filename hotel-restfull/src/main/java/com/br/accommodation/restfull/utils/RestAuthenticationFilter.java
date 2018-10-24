/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.utils;

/**
 *
 * @author MarcosAlexandrede
 */
import java.io.IOException;
import javax.servlet.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestAuthenticationFilter implements Filter {
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
                boolean authenticationStatus = false;
                // better injected
                AuthenticationService authenticationService = new AuthenticationService();
                if (authenticationService.authenticate(authCredentials)) {
                    if ((authenticationService.getUser().getTypeUser() == 1) || (!httpServletRequest.getRequestURI().contains("user") && !httpServletRequest.getRequestURI().contains("bedroom"))){
                        authenticationStatus = true;
                    }

                }
                    

                    if (authenticationStatus) {
                            filter.doFilter(request, response);
                    } else {
                            if (response instanceof HttpServletResponse) {
                                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            }
                    }
            }
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
