package com.xnet.wms;

import com.xnet.wms.entity.Role;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.RoleService;
import com.xnet.wms.service.RoleServiceImpl;
import com.xnet.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WmsApplication {

    static ConfigurableApplicationContext context = null;

    public static void main(String[] args) {
        System.out.println("11111111111111111111111");
        context = SpringApplication.run(WmsApplication.class, args);
        System.out.println("222222222222222222222222f");

        UserService us = context.getBean(UserService.class);
        User u = new User();
        u.setName("Ramy Ashour");
        u.setUsername("admin");
        u.setPassword("admin");
        Role role = new Role();
        role.setName("xxx");

        u.setRole(role);

        us.save(u);

        us.findAll().forEach(user->{
            System.out.println("user: "+user.getName());
        });
        //        Role role = new Role();
                //        role.setName("xx");
                //        
                //        context.getBean(RoleService.class).addRole(role);
                //     
                //
                //        context.getBean(RoleService.class).getAll().forEach(r -> {
                //            System.out.println("New Menu:" + r.getName());
                //        });

    }

}
