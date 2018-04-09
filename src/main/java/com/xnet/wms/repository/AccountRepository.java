/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.Account;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByAccountType_Id(int type );
    List<Account>findByNameContaining(String name);
}
