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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ramy
 */
@Entity(name = "invoice")
public class Invoice extends BaseEntity implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE)
    private Branch branch;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Account account;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    private InvoiceType invoiceType;
    @ManyToOne(cascade = CascadeType.MERGE)
    private PaymentType cashType;
    @ManyToOne(cascade = CascadeType.MERGE)
    private DiscountType discountType;
    private String reference;
    private double discountAmount;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public PaymentType getCashType() {
        return cashType;
    }

    public void setCashType(PaymentType cashType) {
        this.cashType = cashType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<InvoiceItem> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(List<InvoiceItem> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }
}
