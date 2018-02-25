package com.xnet.wms;

import com.xnet.wms.security.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WmsApplication {


    public static void main(String[] args) {
        System.out.println("11111111111111111111111");
        SpringApplication.run(WmsApplication.class, args);
        System.out.println("222222222222222222222222f");
    }
}
