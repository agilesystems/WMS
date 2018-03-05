/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Role;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramy
 */
public interface RoleService {

    Role addRole(Role role);

    Collection<Role> getAll();

}
