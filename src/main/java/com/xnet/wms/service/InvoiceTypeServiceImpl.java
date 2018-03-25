/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.InvoiceType;
import com.xnet.wms.repository.InvoiceRepository;
import com.xnet.wms.repository.InvoiceTypeRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

    @Autowired
    InvoiceTypeRepository invoiceTypeRepository;

    @Override
    public InvoiceType save(InvoiceType invoiceType) {
        if (invoiceType == null) {
            return null;
        }
        return invoiceTypeRepository.save(invoiceType);
    }

    @Override
    public InvoiceType findByID(int id) {
        return invoiceTypeRepository.findOne(id);
    }

    @Override
    public Collection<InvoiceType> findAll() {
        return invoiceTypeRepository.findAll();
    }

}
