/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Tax;
import com.xnet.wms.repository.TaxRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class TaxServiceImp implements TaxService {

    @Autowired
    TaxRepository taxRepository;

    @Override
    public Tax save(Tax tax) {
        return taxRepository.save(tax);
    }

    @Override
    public Tax findById(int id) {
        return taxRepository.findOne(id);
    }

    @Override
    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

}
