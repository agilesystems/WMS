/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.DiscountType;
import com.xnet.wms.repository.DiscountTypeRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class DiscountTypeServiceImpl implements DiscountTypeService {

    @Autowired
    DiscountTypeRepository discountTypeRepository;

    @Override
    public DiscountType findById(int id) {
        return discountTypeRepository.findOne(id);

    }

    @Override
    public Collection<DiscountType> findAll() {
        return discountTypeRepository.findAll();
    }

    @Override
    public DiscountType addNew(DiscountType discountType) {
        return discountTypeRepository.save(discountType);
    }

}
