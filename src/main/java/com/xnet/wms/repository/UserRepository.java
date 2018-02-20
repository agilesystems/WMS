/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.User;
import java.io.Serializable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramy
 */
@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
