/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.Store;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface StoreRepository extends JpaRepository< Store, Integer> {

    Collection<Store> findByNameContainingAndBranch_Id(String name,int branchId);
    Collection<Store> findByBranch_Id(int branchId);
}
