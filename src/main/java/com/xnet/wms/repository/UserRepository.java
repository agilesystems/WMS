/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.User;
import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramy
 */
@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    
//    @Query("select u from User u where u.username = :username and u.branchId.id = :branchid")
    User findByUsernameAndBranch_Id( String username, Integer branchid);
    
    List <User> findAllByBranch_Id(Integer branchId);
}
