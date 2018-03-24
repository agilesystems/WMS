/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Item;
import com.xnet.wms.entity.Store;
import com.xnet.wms.entity.StoreItem;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
public interface StoreItemService {

    /**
     * this method to add new or update current row in store item table
     *
     * @param storeitem an argument specify to get the data to be saved
     * @return true if the row added successfully or false if not added
     */
    StoreItem save(StoreItem storeItem);

    /**
     * This method for soft deleted the store item , just update the deleted
     * field with value(1
     *
     * @param storeItem an argument to specify the data to updated
     * @return true if the row updated or false if not updated
     */
    boolean delete(StoreItem storeItem);

    /**
     * Returns the collection of store items by item id
     *
     * @param item an object parameter to get the collection of store item by
     * the item
     * @return a collection of store items
     */
    Collection<StoreItem> findByItemIdAndStoreId(int itemId, int storeId);

    /**
     * Returns the collection of store items by store id
     *
     * @param store an object parameter to get the collection of store item by
     * the store
     * @return a collection of store items
     */
    Collection<StoreItem> findByStoreId(int storeId);

    Collection<StoreItem> findByItemIdAndBranchId(int itemId, int branchId);

}
