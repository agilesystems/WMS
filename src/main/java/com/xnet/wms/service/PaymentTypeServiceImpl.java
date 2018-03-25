/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.PaymentType;
import com.xnet.wms.repository.PaymentTypeRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Override
    public PaymentType findById(int id) {
        return paymentTypeRepository.findOne(id);
    }

    @Override
    public List<PaymentType> findAll() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public PaymentType save(PaymentType paymentType) {

        return paymentTypeRepository.save(paymentType);
    }

}
