/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.DiscountType;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public interface DiscountTypeService {

    DiscountType findById(int id);

    List<DiscountType> findAll();

    DiscountType save(DiscountType discountType);
}
