/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.StoreItem;
import com.xnet.wms.repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author ramy
 */
@Service
public class StoreItemServiceImpl implements StoreItemService {

    @Autowired
    StoreItemRepository storeItemRepository;

    @Override
    public StoreItem save(StoreItem storeItem) {
        return storeItemRepository.save(storeItem);
    }

    @Override
    public boolean delete(StoreItem storeItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoreItem findByItemId(int itemId) {
        return storeItemRepository.findByItem_Id(itemId);
    }

    @Override
    public Collection<StoreItem> findByStoreId(int storeId) {
        return storeItemRepository.findByStore_Id(storeId);
    }

    @Override
    public Collection<StoreItem> findByItemIdAndBranchId(int itemId, int branchId) {
       return storeItemRepository.findByItem_IdAndBranchId(itemId, branchId);
    }

    @Override
    public List<StoreItem> findAllByBranchId(int branchId) {
        return storeItemRepository.findAllByStore_Branch_Id(branchId);
    }

    @Override
    public List<StoreItem> findAllByBranchIdAndKey(int branchId, String kye) {
//        return storeItemRepository.findAllByStore_Branch_IdAndItem_NameContainingIgnoreCaseOrStore_Branch_IdAndItem_DescriptionContainingIgnoreCaseOrStore_Branch_IdAndItem_BarcodeContainingIgnoreCaseOrStore_Branch_IdAndItem_GlobalIdContainingIgnoreCase(branchId, kye);
        return storeItemRepository.findAllByStore_Branch_IdAndKey(branchId, kye);
    }

    @Override
    public StoreItem findById(int id) {
        return storeItemRepository.findOne(id);
    }

}
