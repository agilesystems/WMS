/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.Role;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public class RoleDTO {

    private int id;
    private String name;
    private Collection<MenuDTO> menus;

    private Role role;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        if (role == null) {
            return;
        }
        id = role.getId();
        name = role.getName();
        this.role = role;
        menus=new ArrayList<MenuDTO>();
        handleRoleMenus();
    }

    private void handleRoleMenus() {
        role.getMenuCollection().stream().filter(p -> p.getParent() == null).forEach(parent -> {
            MenuDTO mDTO = new MenuDTO(parent);
            role.getMenuCollection().stream().filter(sp -> sp.getParent() != null && sp.getParent().getId() == parent.getId()).forEach(sub -> {
                System.out.println("sub>>>>>"+sub.getId());
                mDTO.getSubMenus().add(new MenuDTO(sub));
            });
            menus.add(mDTO);
        });

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

    public Collection<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(Collection<MenuDTO> menus) {
        this.menus = menus;
    }

}
