package com.xnet.wms.model;

public class JwtUser {
    private String userName;
    private int id;
    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
