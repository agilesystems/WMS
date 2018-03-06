/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.Item;
import java.util.Collection;
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

    Item findByBarcode(String barcode);

    /**
     *
     * @param name
     * @return collection of items
     */
//    @Query("Select item from  Item item where item.name LIKE CONCAT('%',:name,'%') AND item.deleted <> 1 ")
    Collection<Item> findByNameContaining(String name);

}
