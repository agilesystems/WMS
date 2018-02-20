package com.xnet.wms.security;

import com.xnet.wms.entity.User;
import com.xnet.wms.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    public User validate(String token) {
        User jwtUser;
        jwtUser = null;
        try {


        String secret = "youtube";
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

            jwtUser = new User();

        jwtUser.setUsername(body.getSubject());
        jwtUser.setId(Integer.parseInt((String)body.get("userId")));
        //jwtUser.setRole((String)body.get("role"));
        } catch (Exception e){
            System.out.println(e);
        }

        return jwtUser;
    }
}
