/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author ramy
 */
@Entity(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    private String url;
    @Column(nullable = false)
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
        setName(name);
        setUrl(url);
        setTitle(title);
        setViewOrder(viewOrder);
        setId(id);
        setParent(parent);


    }

    public Menu(int id, String name, String url, String title, int viewOrder, String icon, Menu parent) {
        setId(id);
        setName(name);
        setUrl(url);
        setTitle(title);
        setViewOrder(viewOrder);
        setId(id);
        setParent(parent);

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
