/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author ramy
 */
@Entity(name = "role")
@Where(clause = "is_deleted=0")
public class Role extends BaseEntity implements Serializable {

    @Column(unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Menu> menusList;

    public Role() {

    }

    public Role(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<Menu> menusList) {
        this.menusList = menusList;
    }

}
