/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.InvoiceType;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public interface InvoiceTypeService {

    InvoiceType addNew(InvoiceType invoiceType);

    InvoiceType findByID(int id);

    Collection<InvoiceType> findAll();
    
}
