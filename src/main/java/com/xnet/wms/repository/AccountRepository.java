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
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByAccountType_Id(int type );
    List<Account>findByNameContaining(String name);
     @Query("select a from account a where "
            + "(a.accountType.id =?1 and upper(a.name) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.address) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.code) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.email) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.mobile1) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.mobile2) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.mobile3) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.mobile2) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.phone1) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.phone2) like concat('%', upper(?2), '%'))"
            + " or (a.accountType.id =?1 and upper(a.phone3) like concat('%', upper(?2), '%'))")
    List<Account> findAllByAccountTypeAndBranch_IdAndKey(int accountTypeId, String key);
}
