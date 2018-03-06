package com.xnet.wms;

import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.Role;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.RoleService;
import com.xnet.wms.service.RoleServiceImpl;
import com.xnet.wms.service.UserService;
import java.util.ArrayList;
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
//        
//        
//        Menu m = new Menu(1,"X_NET", null, "X-NET", 1, null, null);
//        role.setMenusList(new ArrayList<>());
//        role.getMenusList().add(m);
//        Menu sm1 = new Menu(2,"Setting", null, "Setting", 2, null, m);
//        role.getMenusList().add(sm1);
//        
//        
//        sm1 = new Menu(3,"Backup", null, "Backup", 3, null, m);
//        role.getMenusList().add(sm1);
//
//        m = new Menu(4,"PRODUCT", null, "product", 4, null, null);
//        role.getMenusList().add(m);
//        sm1 = new Menu(5,"PRODUCT", null, "Product", 5, null, m);
//        role.getMenusList().add(sm1);
//        sm1 = new Menu(6,"PRODUCT_INQUIRY", null, "PRODUCT INQUIRY", 6, null, m);
//        role.getMenusList().add(sm1);
//        sm1 = new Menu(7,"PRODUCT_TRANSACTION_REPORT", null, "PRODUCT_TRANSACTION_REPORT", 7, null, m);
//        role.getMenusList().add(sm1);
        role.setName("Admin");
//
        u.setRole(role);

     //   us.save(u);

//        us.findAll().forEach(user -> {
//            System.out.println("user: " + user.getName() + "Role:" + user.getRole().getName() + " Menus:");
//            user.getRole().getMenusList().forEach(mm -> {
//                System.out.println(mm.getName());
//            });
//        });
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
