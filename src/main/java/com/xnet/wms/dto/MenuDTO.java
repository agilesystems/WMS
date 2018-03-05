/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Menu;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public class MenuDTO {

    private int id;
    private String title;
    private Integer viewOrder;
    private String url;
    private String icon;
    private Collection<MenuDTO> subMenus;
    private boolean isParent;

    public MenuDTO() {

    }

    public MenuDTO(Menu menu) {
        if (menu == null) {
            return;
        }
        id = menu.getId();
        title = menu.getTitle();
        viewOrder = menu.getViewOrder();
        url = menu.getUrl();
        icon = menu.getIcon();
        isParent = (menu.getParent() == null);
        subMenus=new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Collection<MenuDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Collection<MenuDTO> subMenus) {
        this.subMenus = subMenus;
    }

    public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

}
