/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Where;

/**
 * @author ramy
 */
@Entity(name = "invoice")
@Where(clause = "is_deleted=0")
public class Invoice extends BaseEntity implements Serializable {

    @ManyToOne
    private Branch branch;
    @ManyToOne
    private Account account;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate;
    @ManyToOne
    private InvoiceType invoiceType;
    private double invoiceAmount;
    private double invoiceAmountAfterDiscount;
    private double invoiceAmountAfterTax;
    private double invoiceNetAmount;
    private double invoiceOutstandingAmount;
    @ManyToOne
    private PaymentType paymentType;
    private String reference;
    private double discount1Percentage;
    private double discount1Amount;
    private double discount2Percentage;
    private double discount2Amount;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItemsList;
    private double tax1Percentage;
    private double tax1Amount;
    private double tax2Percentage;
    private double tax2Amount;
    private double tax3Percentage;
    private double tax3Amount;
    private double tax4Percentage;
    private double tax4Amount;
    private double tax5Percentage;
    private double tax5Amount;

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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<InvoiceItem> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(List<InvoiceItem> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }

    public double getTax1Percentage() {
        return tax1Percentage;
    }

    public void setTax1Percentage(double tax1Percentage) {
        this.tax1Percentage = tax1Percentage;
    }

    public double getTax1Amount() {
        return tax1Amount;
    }

    public void setTax1Amount(double tax1Amount) {
        this.tax1Amount = tax1Amount;
    }

    public double getTax2Percentage() {
        return tax2Percentage;
    }

    public void setTax2Percentage(double tax2Percentage) {
        this.tax2Percentage = tax2Percentage;
    }

    public double getTax2Amount() {
        return tax2Amount;
    }

    public void setTax2Amount(double tax2Amount) {
        this.tax2Amount = tax2Amount;
    }

    public double getTax3Percentage() {
        return tax3Percentage;
    }

    public void setTax3Percentage(double tax3Percentage) {
        this.tax3Percentage = tax3Percentage;
    }

    public double getTax3Amount() {
        return tax3Amount;
    }

    public void setTax3Amount(double tax3Amount) {
        this.tax3Amount = tax3Amount;
    }

    public double getTax4Percentage() {
        return tax4Percentage;
    }

    public void setTax4Percentage(double tax4Percentage) {
        this.tax4Percentage = tax4Percentage;
    }

    public double getTax4Amount() {
        return tax4Amount;
    }

    public void setTax4Amount(double tax4Amount) {
        this.tax4Amount = tax4Amount;
    }

    public double getTax5Percentage() {
        return tax5Percentage;
    }

    public void setTax5Percentage(double tax5Percentage) {
        this.tax5Percentage = tax5Percentage;
    }

    public double getTax5Amount() {
        return tax5Amount;
    }

    public void setTax5Amount(double tax5Amount) {
        this.tax5Amount = tax5Amount;
    }

    public double getDiscount1Percentage() {
        return discount1Percentage;
    }

    public void setDiscount1Percentage(double discount1Percentage) {
        this.discount1Percentage = discount1Percentage;
    }

    public double getDiscount1Amount() {
        return discount1Amount;
    }

    public void setDiscount1Amount(double discount1Amount) {
        this.discount1Amount = discount1Amount;
    }

    public double getDiscount2Percentage() {
        return discount2Percentage;
    }

    public void setDiscount2Percentage(double discount2Percentage) {
        this.discount2Percentage = discount2Percentage;
    }

    public double getDiscount2Amount() {
        return discount2Amount;
    }

    public void setDiscount2Amount(double discount2Amount) {
        this.discount2Amount = discount2Amount;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public double getInvoiceAmountAfterDiscount() {
        return invoiceAmountAfterDiscount;
    }

    public void setInvoiceAmountAfterDiscount(double invoiceAmountAfterDiscount) {
        this.invoiceAmountAfterDiscount = invoiceAmountAfterDiscount;
    }

    public double getInvoiceAmountAfterTax() {
        return invoiceAmountAfterTax;
    }

    public void setInvoiceAmountAfterTax(double invoiceAmountAfterTax) {
        this.invoiceAmountAfterTax = invoiceAmountAfterTax;
    }

    public double getInvoiceNetAmount() {
        return invoiceNetAmount;
    }

    public void setInvoiceNetAmount(double invoiceNetAmount) {
        this.invoiceNetAmount = invoiceNetAmount;
    }

    public double getInvoiceOutstandingAmount() {
        return invoiceOutstandingAmount;
    }

    public void setInvoiceOutstandingAmount(double invoiceOutstandingAmount) {
        this.invoiceOutstandingAmount = invoiceOutstandingAmount;
    }

}
