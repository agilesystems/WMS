/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.Branch;
import com.xnet.wms.entity.PyementType;
import com.xnet.wms.entity.DiscountType;
import com.xnet.wms.entity.Invoice;
import com.xnet.wms.entity.InvoiceItem;
import com.xnet.wms.entity.InvoiceType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramy
 */
public class InvoiceDTO {

    private int id;
    private Branch branch;
    private Account account;
    private Date invoiceDate;
    private InvoiceType invoiceType;
    private PyementType cashType;
    private DiscountType discountType;
    private String reference;
    private double discountAmount;
    private List<InvoiceItem> invoiceItemsList;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Invoice invoice) {
        if (invoice == null) {
            return;
        }
        setId(invoice.getId());
        setBranch(invoice.getBranch());
        setAccount(invoice.getAccount());
        setInvoiceDate(invoice.getInvoiceDate());
        setCashType(invoice.getCashType());
        setDiscountAmount(invoice.getDiscountAmount());
        setDiscountType(invoice.getDiscountType());
        setReference(invoice.getReference());
        setInvoiceItemsList(invoice.getInvoiceItemsList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public PyementType getCashType() {
        return cashType;
    }

    public void setCashType(PyementType cashType) {
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
        if (invoiceItemsList == null) {
            return;
        }
        this.invoiceItemsList = invoiceItemsList;
    }

}
