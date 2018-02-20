/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.User;
import com.xnet.wms.entity.UserMenu;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public class UserDTO {

    public int id;
    public String name;
    public String userName;
    public Collection<UserMenu> menus;

    public UserDTO() {
    }

    public UserDTO(User user) {
        if (user == null) {
            return;
        }
        this.id = user.getId();
        this.name = user.getFirstname() + " " + user.getLastname();
        this.userName = user.getUsername();
        menus = user.getUserMenuCollection();
    }

}
