/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Item;
import com.xnet.wms.repository.ItemRepository;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author mhdsy
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addNew(Item item) {
        if (item == null) {
            return null;
        }
        return itemRepository.save(item);
    }

    @Override
    public boolean delete(Item item) {

        itemRepository.delete(item);
        return true;
    }

    @Override
    public Collection<Item> findAll() {
        if (!itemRepository.findAll().isEmpty()) {
            return itemRepository.findAll();
        } else {
            return null;
        }
    }

    @Override
    public Collection<Item> findByGroupid(int groupid) {
//        if (!itemRepository.findByGroupid(groupid).isEmpty()) {
//            return itemRepository.findByGroupid(groupid);
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public Collection<Item> findLowestquantity(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findByBarcode(String barcode) {
        Item item = itemRepository.findByBarcode(barcode);
        if (item != null) {
            return item;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Item> getItemExbiredate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Item> getByName(String Name) {
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

}
