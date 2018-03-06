/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "menu")
public class Menu {

    @Id
    private int id;
    private String name;
    private String url;
    private String title;
    private int viewOrder;
    private String icon;
    @ManyToOne(cascade = CascadeType.ALL)
    private Menu parent;
    @OneToMany(mappedBy = "parent")
    private List<Menu> subMenusList;

    public Menu() {

    }

    public Menu(String name, String url, String title, int viewOrder, String icon, Menu parent) {
        this.name = name;
        this.url = url;
        this.title = title;
        this.viewOrder = viewOrder;
        this.icon = icon;
        this.parent = parent;

    }

    public Menu(int id, String name, String url, String title, int viewOrder, String icon, Menu parent) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.title = title;
        this.viewOrder = viewOrder;
        this.icon = icon;
        this.parent = parent;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(int viewOrder) {
        this.viewOrder = viewOrder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getSubMenusList() {
        return subMenusList;
    }

    public void setSubMenusList(List<Menu> subMenusList) {
        this.subMenusList = subMenusList;
    }

}
