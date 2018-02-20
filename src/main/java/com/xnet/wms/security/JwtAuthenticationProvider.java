/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.security;

import com.xnet.wms.entity.User;
import com.xnet.wms.model.JwtAuthenticationToken;
import com.xnet.wms.model.JwtUser;
import com.xnet.wms.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author mhdsy
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        User jwtUser = validator.validate(token);

        if (jwtUser == null) {
            throw new RuntimeException("Token is not valid!!!");
        }

        /*List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());*/

        return new JwtUserDetails(
                jwtUser.getUsername()
                ,jwtUser.getId(),
                token);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return JwtAuthenticationToken.class.isAssignableFrom(aClass);
    }
    
}
