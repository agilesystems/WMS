/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.User;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public class UserDTO {

    private int id;
    private String name;
    private String userName;
    private RoleDTO role;

    public UserDTO() {
    }

    public UserDTO(User user) {
        if (user == null) {
            return;
        }
        this.id = user.getId();
        this.name = user.getFirstname() + " " + user.getLastname();
        this.userName = user.getUsername();
        this.role = new RoleDTO(user.getRole());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    
}
