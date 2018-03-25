/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.PaymentType;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface PaymentTypeService {

    PaymentType findById(int id);

    List<PaymentType> findAll();
    
    PaymentType save(PaymentType paymentType);
}
