/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Item;
import com.xnet.wms.entity.Store;
import com.xnet.wms.entity.StoreItem;
import com.xnet.wms.repository.ItemRepository;
import com.xnet.wms.repository.StoreItemRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author mhdsy
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    StoreItemRepository storeItemRepository;
    @Autowired
    StoreItemService storeItemService;

    @Override
    public Item save(Item item) {
        if (item == null) {
            return null;
        }
        if (storeItemRepository.findByItem_Id(item.getId()) != null) {
            //update the current item if exist
            return itemRepository.save(item);
        } else {
            // add item and add store item  withe default data
            itemRepository.save(item);
            StoreItem storeItem = new StoreItem();
            storeItem.setAvailableQuantity(0);
            storeItem.setDiscountPercentage(0);
            storeItem.setPrice(0);
            storeItem.setItem(item);
//            storeItem.setStore(null);
            storeItemRepository.save(storeItem);
            return item;
        }
    }

    @Override
    public boolean delete(Item item) {
        if (storeItemService.findById(item.getId()).getAvailableQuantity() > 0) {
            return false;
        } else {
            itemRepository.delete(item);
            return true;
        }

    }

    @Override
    public List<Item> findAll() {
        if (!itemRepository.findAll().isEmpty()) {
            return itemRepository.findAll();
        } else {
            return null;
        }
    }

    @Override
    public List<Item> findByGroupid(int groupid) {
//        if (!itemRepository.findByGroupid(groupid).isEmpty()) {
//            return itemRepository.findByGroupid(groupid);
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public List<Item> findLowestquantity(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findByBarcode(String barcode) {
        Item item = itemRepository.findByGlobalBarcode(barcode);
        if (item != null) {
            return item;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getItemExbiredate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getByName(String Name) {
        Collection<Item> item = itemRepository.findByNameContaining(Name);
        if (item != null) {
            return itemRepository.findByNameContaining(Name);
        } else {
            return null;
        }
    }

    @Override
    public Item findByExbiredate(Date date) {

//        if (itemRepository.findByExbiredate(date) != null) {
//            return itemRepository.findByExbiredate(date);
//        } else {
//            return null;
//        }
        return null;

    }

    @Override
    public List<Item> findAllByKey(String key) {
        return itemRepository.findAllByKey(key);
    }

    @Override
    public List<Item> findByStore(int storeId) {
        List<Item> i = new ArrayList<>();

        for (StoreItem storeItem : storeItemService.findByStoreId(storeId)) {
            i.add(storeItem.getItem());
        }
        return i;
    }

}
