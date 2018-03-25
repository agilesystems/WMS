/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ramy
 */
public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobile;
    private String address;
    private RoleDTO role;
    private BranchDTO branch;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(User user) {
        if (user == null) {
            return;
        }
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.role = new RoleDTO(user.getRole());
        this.branch = new BranchDTO(user.getBranch());
        this.phone = user.getPhone();
        this.mobile = user.getMobile();
        this.address = user.getAddress();

        if (user.getPassword().equals(DigestUtils.sha1Hex("123456"))) {
            this.password = "123456";
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
