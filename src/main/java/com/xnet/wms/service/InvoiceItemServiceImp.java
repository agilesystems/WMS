/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.InvoiceItem;
import com.xnet.wms.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
@Service
public class InvoiceItemServiceImp implements InvoiceItemService {

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Override
    public boolean save(InvoiceItem invoiceItem) {

        return invoiceItemRepository.save(invoiceItem) != null;
    }

    @Override
    public boolean delete(InvoiceItem invoiceitem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemtotal(InvoiceItem invoiceitem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemdiscount(InvoiceItem invoiceitem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemnetvalue(InvoiceItem invoiceitem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
