/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.Item;
import com.xnet.wms.entity.StoreItem;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByGlobalBarcode(String barcode);

    /**
     *
     * @param name
     * @return collection of items
     */
//    @Query("Select item from  Item item where item.name LIKE CONCAT('%',:name,'%') AND item.deleted <> 1 ")
    List<Item> findByNameContaining(String name);


    @Query("select i from item i where "
            + "   upper(i.name) like concat('%', upper(?2), '%') "
            + "or upper(i.description) like concat('%', upper(?2), '%') )"
            + "or upper(i.globalBarcode) like concat('%', upper(?2), '%') "
            + "or upper(i.globalId) like concat('%', upper(?2), '%') ")
    List<Item> findAllByBranch_IdAndKey(int branchId, String key);

}
