/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ramy
 */
@Entity(name = "invoice")
public class Invoice implements Serializable {

    @Id
    Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    Branch branch;
    @ManyToOne(cascade = CascadeType.ALL)
    Account account;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date invoiceDate;
    @ManyToOne(cascade = CascadeType.ALL)
    InvoiceType invoiceType;
    @ManyToOne(cascade = CascadeType.ALL)
    CashType cashType;
    @ManyToOne(cascade = CascadeType.ALL)
    DiscountType discountType;
    String reference;
    double discountAmount;
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> invoiceItemsList;

//    
//  `discount_value` float DEFAULT NULL,
//  `tax1per` int(11) DEFAULT NULL,
//  `tax1val` float DEFAULT NULL,
//  `tax2per` int(11) DEFAULT NULL,
//  `tax2val` float DEFAULT NULL,
//  `tax3per` int(11) DEFAULT NULL,
//  `tax3val` float DEFAULT NULL,
//  `total` float DEFAULT NULL,
//  `paied` float DEFAULT NULL,
//  `net` float DEFAULT NULL,
//  `outstanding` float DEFAULT NULL,
//  `created_by` int(11) DEFAULT NULL,
//  `create_date` date DEFAULT NULL,
//  `updated_by` int(11) DEFAULT NULL,
//  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
//  `deleted` bit(1) DEFAULT NULL,
//  `deletedby` int(11) DEFAULT NULL,
}
