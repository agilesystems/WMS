/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.StoreItem;
import com.xnet.wms.repository.StoreItemRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
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
    public Collection<StoreItem> findByItemIdAndStoreId(int itemId, int storeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<StoreItem> findByStoreId(int storeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<StoreItem> findByItemIdAndBranchId(int itemId, int branchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
