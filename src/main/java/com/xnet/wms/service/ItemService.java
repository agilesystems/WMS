/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Item;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Muhammad
 */
public interface ItemService {

    /**
     * This Method to add new Or update Current item
     *
     * @param item An object of item parameter to addNew the item data
     * @return true if the item saves successfully Or false if not saved
     * successfully .
     */
    Item save(Item item);

    /**
     * This method for soft deleted the items , just update the deleted field
     * with value(1
     *
     * @param id an Object parameter to update item
     * @return true if the item updated with deleted field or false if not
     * updated
     */
    boolean delete(Item item);

    /**
     * this method to get all item
     *
     * @return a collection of item
     */
    List<Item> findAll();

    /**
     * this method to get the items by group id
     *
     * @param groupid an integer parameter to get the items
     * @return a collection of item by group id
     */
    List<Item> findByGroupid(int groupid);

    /**
     * this method to get the items by the lowest quantity if the current
     * quantity is near the lowest quantity or equal
     *
     * @param item an object parameter to get items if the lowest quantity is
     * near current quantity or equal .
     * @return a collection of items if the lowest quantity near current
     * quantity or equal
     */
    List<Item> findLowestquantity(Item item);

    /**
     * this method to find item by bacrcode
     *
     * @param barcode an String parameter to determined the item barcode
     * @return item object if find the item by barcode
     */
    Item findByBarcode(String barcode);

    /**
     * this method to get the items that the expire date between from date and
     * to date
     *
     * @param from an date argument to specify the start date of expire
     * @param to an date argument to specify the end date of expire
     * @return a collection of item if the expire item between the from and to
     * date .
     */
    List<Item> getItemExbiredate(Date from, Date to);

    /**
     *
     * @param Name
     * @return
     */
    List<Item> getByName(String Name);

    /**
     *
     * @param date
     * @return
     */
    Item findByExbiredate(Date date);

    List<Item> findAllByBranch_IdAndKey(int branchId, String key);

}
