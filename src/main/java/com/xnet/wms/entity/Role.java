/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Where;

/**
 *
 * @author ramy
 */
@Entity(name = "role")
public class Role extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "role")
    private List<User> usersList;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private Collection<Menu> menusList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Menu> getMenusList() {
        return menusList;
    }

    public void setMenusList(Collection<Menu> menusList) {
        this.menusList = menusList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

}
