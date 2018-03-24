/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ramy
 * Created: Mar 6, 2018
 */

-- use inventory;
-- INSERT INTO `inventory`.`branch` (`name`,`created_date`,is_deleted) VALUES ('branch 1',now(),0);
-- 
-- INSERT INTO `inventory`.`role` (`id`, `created_date`, `is_deleted`,  `name`, `created_by_id`)
--  VALUES ('1', '2018-03-06 16:10:58', 0,  'Admin', null);
-- 
-- INSERT INTO `inventory`.`user` (`id`,is_deleted,is_blocked, `created_date`, `name`, `password`, `username`, `role_id`,`branch_id`)
--  VALUES ('1',0,0, '2018-03-06 16:10:58', 'Ramy Ashour', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin', '1',1);
-- 
-- INSERT INTO `menu` VALUES 
-- (1,null,'X_NET','X-Net',NULL,1,NULL),
-- (2,null,'PRODUCT','البضاعة',NULL,2,NULL),
-- (3,null,'FIANANCIAL','الحسابات',NULL,3,NULL),
-- (4,null,'CASH','الخزينة',NULL,4,NULL),
-- (5,null,'INVOICE','الفواتير',NULL,5,NULL),
-- (6,null,'REPORT','تقارير',NULL,6,NULL),
-- (7,null,'HELP','مساعدة',NULL,8,NULL),
-- (8,null,'SETTING','إعداد البرنامج',NULL,101,1),
-- (9,null,'BACKUP','عمل نسخة احتياطية',NULL,102,1),
-- (13,null,'PRODUCT','البضاعة',NULL,201,2),
-- (14,null,'PRODDUCT_INQIRY','استعلام عن صنف',NULL,202,2),
-- (15,null,'PRODUCT_TRANSACTION_REPORT','تقرير حركة الصنف',NULL,203,2),
-- (16,null,'STORE_ITEMS_REPORT','تقرير بضاعة مخزن',NULL,204,2),
-- (17,null,'STORE_TRANSACTION_REPORT','تقرير حركة مخزن',NULL,205,2),
-- (18,null,'PRODUCT_LESS_THAN_MIN','اصناف كميتها اقل من حد الطلب',NULL,206,2),
-- (12,null,'ADD_USER','اضافة مستخدم','user/add-user.html',103,1),
-- (11,null,'ACCOUNT','العملاء',NULL,7,NULL),
-- (10,null,'ADD_ACCOUNT','اضافة عميل','account/add-account.html',2001,11),
-- (19,null,'INVOICE_ADD','إضافة فاتورة',NULL,501,5);
-- 
-- 
-- insert into role_menus_list values
-- (1,1),
-- (1,2),
-- (1,3),
-- (1,4),
-- (1,5),
-- (1,6),
-- (1,7),
-- (1,8),
-- (1,9),
-- (1,10),
-- (1,11),
-- (1,12),
-- (1,13),
-- (1,14),
-- (1,15),
-- (1,16),
-- (1,17),
-- (1,18),
-- (1,19);
