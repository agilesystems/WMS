/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.UserDTO;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
            HttpServletResponse response) throws IOException {

        String token = null;

        User user = userService.authenticated(username, password);

        Map<String, Object> tokenMap = new HashMap<String, Object>();
        if (user != null) {
            UserDTO u = new UserDTO(user);

//            token=return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .setId(UUID.randomUUID().toString())
//                .setExpiration(setExpiry(expiresIn))
//                .signWith(this.Algo, this.getEncodedSecret())
//                .compact();
            token = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            tokenMap.put("user", u);
            return new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
        }

    }
}
