/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.StoreItem;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Integer> {

    List<StoreItem> findAllByStore_Branch_Id(int branchId);
    @Query("select i from store_item i where "
            + "   ( i.store.branch.id=?1 and upper(i.item.name) like concat('%', upper(?2), '%') )"
            + "or ( i.store.branch.id=?1 and upper(i.item.description) like concat('%', upper(?2), '%') ) "
            + "or ( i.store.branch.id=?1 and upper(i.item.globalBarcode) like concat('%', upper(?2), '%') )"
            + "or ( i.store.branch.id=?1 and upper(i.item.globalId) like concat('%', upper(?2), '%') )")            
    List<StoreItem> findAllByStore_Branch_IdAndKey(int branchId,String key);
}
