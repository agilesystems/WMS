package com.xnet.wms.security;

import com.xnet.wms.entity.User;
import com.xnet.wms.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(User jwtUser){
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("userId",String.valueOf(jwtUser.getId()));
        //claims.put("role",jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
