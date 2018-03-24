package com.xnet.wms;

import com.xnet.wms.entity.Branch;
import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.Role;
import com.xnet.wms.entity.User;
import com.xnet.wms.repository.MenuRepository;
import com.xnet.wms.service.BranchService;
import com.xnet.wms.service.MenuService;
import com.xnet.wms.service.RoleService;
import com.xnet.wms.service.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WmsApplication {

    static ConfigurableApplicationContext context = null;

    static String dbServer = "localhost";
    static String dbUser = "root";
    static String dbPass = "123456";

//    @Autowired
    BranchService branchService;

//    @Autowired
    UserService userService;

//    @Autowired
    RoleService roleService;

//    @Autowired
    MenuService menuService;

    public static void main(String[] args) throws SQLException {
        System.out.println("11111111111111111111111");

        createDatabase();

        context = SpringApplication.run(WmsApplication.class, args);
        new WmsApplication().insertData();
//        insertInitData();
        System.out.println("222222222222222222222222f");
    }

    public static void createDatabase() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://" + dbServer + "/mysql?"
                        + "user=" + dbUser + "&password=" + dbPass + "&useSSL=false");
        String dbName = "inventory";
        Statement stm = connection.createStatement();
        stm.execute("drop database inventory;");

        stm.execute("create database " + dbName + " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;");
        stm.close();
        connection.close();
    }

    public void insertData() {

        branchService = context.getBean(BranchService.class);
        roleService = context.getBean(RoleService.class);
        userService = context.getBean(UserService.class);
        menuService = context.getBean(MenuService.class);
        Branch branch = branchService.save(new Branch("Main Branch", "HQ Address"));
        Role role = roleService.save(new Role("Admin"));
        role.setMenusList(new ArrayList<Menu>());
        role.getMenusList().add(menuService.save(new Menu(1, "X_NET", null, "X_NET", 1, null, null)));
        role.getMenusList().add(menuService.save(new Menu(2, "PRODUCT", null, "PRODUCT", 2, null, null)));
        role.getMenusList().add(menuService.save(new Menu(3, "FIANANCIAL", null, "FIANANCIAL", 3, null, null)));
        role.getMenusList().add(menuService.save(new Menu(4, "CASH", null, "CASH", 4, null, null)));
        role.getMenusList().add(menuService.save(new Menu(5, "INVOICE", null, "INVOICE", 5, null, null)));
        role.getMenusList().add(menuService.save(new Menu(6, "REPORT", null, "REPORT", 6, null, null)));
        role.getMenusList().add(menuService.save(new Menu(7, "ACCOUNT", null, "ACCOUNT", 7, null, null)));
        role.getMenusList().add(menuService.save(new Menu(8, "HELP", null, "HELP", 7, null, null)));
        role.getMenusList().add(menuService.save(new Menu(9, "SETTING", null, "SETTING", 101, null, role.getMenusList().get(0))));
        role.getMenusList().add(menuService.save(new Menu(10, "BACKUP", null, "BACKUP", 102, null, role.getMenusList().get(0))));
        role.getMenusList().add(menuService.save(new Menu(11, "PRODUCT", null, "PRODUCT", 201, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(12, "PRODDUCT_INQIRY", null, "PRODDUCT_INQIRY", 202, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(13, "PRODUCT_TRANSACTION_REPORT", null, "PRODUCT_TRANSACTION_REPORT", 203, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(14, "STORE_ITEMS_REPORT", null, "STORE_ITEMS_REPORT", 204, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(15, "STORE_TRANSACTION_REPORT", null, "STORE_TRANSACTION_REPORT", 205, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(16, "PRODUCT_LESS_THAN_MIN", null, "PRODUCT_LESS_THAN_MIN", 206, null, role.getMenusList().get(1))));
        role.getMenusList().add(menuService.save(new Menu(17, "ADD_USER", null, "ADD_USER", 103, null, role.getMenusList().get(0))));
        role.getMenusList().add(menuService.save(new Menu(18, "ADD_ACCOUNT", null, "ADD_ACCOUNT", 701, null, role.getMenusList().get(6))));
        role.getMenusList().add(menuService.save(new Menu(19, "ADD_INVOICE", null, "ADD_INVOICE", 501, null, role.getMenusList().get(4))));
        role = roleService.save(role);
        User user = userService.save(new User("Admin", "admin", "admin", branch, role));

        System.out.println("User>>" + user.toString());

        user = null;
        role = null;
        branch = null;
        System.gc();

    }

    public static void insertInitData() throws SQLException {

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://" + dbServer + "/mysql?"
                        + "user=" + dbUser + "&password=" + dbPass + "&useSSL=false")) {
            Statement stm = connection.createStatement();
            stm.execute("use inventory;");
            stm.execute("INSERT INTO `inventory`.`branch` (`name`,`created_date`,is_deleted) VALUES ('branch 1',now(),0);");
            stm.execute("INSERT INTO `inventory`.`role` (`id`, `created_date`, `is_deleted`,  `name`, `created_by_id`)\n"
                    + " VALUES ('1', '2018-03-06 16:10:58', 0,  'Admin', null);");
            stm.execute("INSERT INTO `inventory`.`user` (`id`,is_deleted,is_blocked, `created_date`, `name`, `password`, `username`, `role_id`,`branch_id`)\n"
                    + " VALUES ('1',0,0, '2018-03-06 16:10:58', 'Ramy Ashour', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin', '1',1);");
            stm.execute("INSERT INTO `menu` VALUES \n"
                    + "(1,null,'X_NET','X-Net',NULL,1,NULL),\n"
                    + "(2,null,'PRODUCT','البضاعة',NULL,2,NULL),\n"
                    + "(3,null,'FIANANCIAL','الحسابات',NULL,3,NULL),\n"
                    + "(4,null,'CASH','الخزينة',NULL,4,NULL),\n"
                    + "(5,null,'INVOICE','الفواتير',NULL,5,NULL),\n"
                    + "(6,null,'REPORT','تقارير',NULL,6,NULL),\n"
                    + "(7,null,'HELP','مساعدة',NULL,8,NULL),\n"
                    + "(8,null,'SETTING','إعداد البرنامج',NULL,101,1),\n"
                    + "(9,null,'BACKUP','عمل نسخة احتياطية',NULL,102,1),\n"
                    + "(13,null,'PRODUCT','البضاعة',NULL,201,2),\n"
                    + "(14,null,'PRODDUCT_INQIRY','استعلام عن صنف',NULL,202,2),\n"
                    + "(15,null,'PRODUCT_TRANSACTION_REPORT','تقرير حركة الصنف',NULL,203,2),\n"
                    + "(16,null,'STORE_ITEMS_REPORT','تقرير بضاعة مخزن',NULL,204,2),\n"
                    + "(17,null,'STORE_TRANSACTION_REPORT','تقرير حركة مخزن',NULL,205,2),\n"
                    + "(18,null,'PRODUCT_LESS_THAN_MIN','اصناف كميتها اقل من حد الطلب',NULL,206,2),\n"
                    + "(12,null,'ADD_USER','اضافة مستخدم','user/add-user.html',103,1),\n"
                    + "(11,null,'ACCOUNT','العملاء',NULL,7,NULL),\n"
                    + "(10,null,'ADD_ACCOUNT','اضافة عميل','account/add-account.html',2001,11),\n"
                    + "(19,null,'INVOICE_ADD','إضافة فاتورة',NULL,501,5);");
            stm.execute("insert into role_menus_list values\n"
                    + "(1,1),\n"
                    + "(1,2),\n"
                    + "(1,3),\n"
                    + "(1,4),\n"
                    + "(1,5),\n"
                    + "(1,6),\n"
                    + "(1,7),\n"
                    + "(1,8),\n"
                    + "(1,9),\n"
                    + "(1,10),\n"
                    + "(1,11),\n"
                    + "(1,12),\n"
                    + "(1,13),\n"
                    + "(1,14),\n"
                    + "(1,15),\n"
                    + "(1,16),\n"
                    + "(1,17),\n"
                    + "(1,18),\n"
                    + "(1,19);");

            stm.close();
            connection.close();
        }
    }

}
