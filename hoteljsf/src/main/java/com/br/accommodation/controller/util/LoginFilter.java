/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.controller.util;

/**
 *
 * @author JoaoPaulo
 */
import com.br.accommodation.controller.LoginController;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(servletNames = {"Faces Servlet"})
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
//        User user = null;
//        HttpSession sess = ((HttpServletRequest) request).getSession();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse)response;
//        if (sess != null) {
//            user = (User) sess.getAttribute("usuarioLogado");
//        }
//req.getRequestURI().endsWith("login.xhtml") || req.getRequestURI().contains("javax.faces.resource/")
        LoginController loginBean = (LoginController)req.getSession().getAttribute("loginController");
        if (loginBean == null || !loginBean.isLoggedIn()) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/faces/login/login.xhtml");
        }
//        chain.doFilter(request, response);
//        if(loginBean.isLoggedIn())
        if ((loginBean != null && loginBean.getUser().getTypeUser() == 1) || (!req.getRequestURI().contains("user") && !req.getRequestURI().contains("bedroom"))) {
            chain.doFilter(request, response);
        } else {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/faces/error.xhtml");
//            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Not Acept");
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
