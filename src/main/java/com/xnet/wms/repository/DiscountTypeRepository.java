/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ramy
 */
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer> {
    
}
