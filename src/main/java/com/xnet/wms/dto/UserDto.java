/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.User;

/**
 *
 * @author mhdsy
 */
public class UserDto {
    private int id;
    private String name;
    private String password;
    
    public UserDto(){
}
    
    public UserDto(User user) {
//        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getUsername();
//        this.employeeId = user.getEmployeeId().getId();
//        this.employeeName = user.getEmployeeId().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
