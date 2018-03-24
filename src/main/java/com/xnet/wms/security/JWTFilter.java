/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.security;

import com.xnet.wms.service.UserService;
import com.xnet.wms.service.UserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A generic filter for security. I will check token present in the header.
 *
 * @author Sarath Muraleedharan
 *
 */
//@Component
public class JWTFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "roles";

  

   

    @Autowired
    UserService userService;
//= new UserServiceImpl();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURL().toString();
        System.out.println("Http Request come to " + url);
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
            System.out.println("Http Request Rejected");
        } else {

            try {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
                String username = claims.get("sub").toString();
                if (new UserServiceImpl().hasAccess(username, "index_.html")) {
                    System.out.println(username + "ssssssssssssss");
                    filterChain.doFilter(req, res);
                    return;
                }
            } catch (SignatureException e) {
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            }

        }
    }

    /**
     * Method for creating Authentication for Spring Security Context Holder
     * from JWT claims
     *
     * @param claims
     * @return
     */
    public Authentication getAuthentication(Claims claims) {
       
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString()));
        User principal;
        System.out.println(claims);
        principal = new User(claims.getSubject(), "", authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                principal, "", authorities);
        return usernamePasswordAuthenticationToken;
    }
}
