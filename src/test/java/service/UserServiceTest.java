/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.xnet.wms.entity.User;
import com.xnet.wms.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ramy
 */
public class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void addNewUser() {
        User u = new User();
        u.setAddress("Address");

        Assert.assertEquals(1, 1);
    }
}
