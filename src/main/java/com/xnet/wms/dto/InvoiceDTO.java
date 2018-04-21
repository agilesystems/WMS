/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Invoice;
import com.xnet.wms.entity.InvoiceType;
import com.xnet.wms.entity.PaymentType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramy
 */
public class InvoiceDTO {

    private int id;
    private BranchDTO branchDTO;
    private AccountDTO accountDTO;
    private Date invoiceDate;
    private InvoiceType invoiceType;
    private PaymentType PaymentType;
    private String reference;
    private double invoiceAmount;
    private double invoiceAmountAfterDiscount;
    private double invoiceAmountAfterTax;
    private double invoiceNetAmount;
    private double invoiceOutstandingAmount;
    private double discount1Percentage;
    private double discount1Amount;
    private double discount2Percentage;
    private double discount2Amount;
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
    private List<InvoiceItemDTO> invoiceItemsListDTO;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Invoice invoice) {
        if (invoice == null) {
            return;
        }
        setId(invoice.getId());
        setBranchDTO(new BranchDTO(invoice.getBranch()));
        setAccountDTO(new AccountDTO(invoice.getAccount()));
        setInvoiceDate(invoice.getInvoiceDate());
        setPaymentType(invoice.getPaymentType());

        setReference(invoice.getReference());

        setInvoiceItemsDTOList(new ArrayList<>());
        invoice.getInvoiceItemsList().forEach(i -> {
            getInvoiceItemsDTOList().add(new InvoiceItemDTO(i));
        });

        setInvoiceAmount(invoice.getInvoiceAmount());
        setInvoiceAmountAfterDiscount(invoice.getInvoiceAmountAfterDiscount());
        setInvoiceAmountAfterTax(invoice.getInvoiceAmountAfterTax());
        setInvoiceNetAmount(invoice.getInvoiceNetAmount());
        setInvoiceOutstandingAmount(invoice.getInvoiceOutstandingAmount());
        setDiscount1Amount(invoice.getDiscount1Amount());
        setDiscount1Percentage(invoice.getDiscount1Percentage());
        setDiscount2Amount(invoice.getDiscount2Amount());
        setDiscount2Percentage(invoice.getDiscount2Percentage());
        setTax1Amount(invoice.getTax1Amount());
        setTax1Percentage(invoice.getTax1Percentage());
        setTax2Amount(invoice.getTax2Amount());
        setTax2Percentage(invoice.getTax2Percentage());
        setTax3Amount(invoice.getTax3Amount());
        setTax3Percentage(invoice.getTax3Percentage());
        setTax4Amount(invoice.getTax4Amount());
        setTax4Percentage(invoice.getTax4Percentage());
        setTax5Amount(invoice.getTax5Amount());
        setTax5Percentage(invoice.getTax5Percentage());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BranchDTO getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
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
        return PaymentType;
    }

    public void setPaymentType(PaymentType PaymentType) {
        this.PaymentType = PaymentType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<InvoiceItemDTO> getInvoiceItemsDTOList() {
        return invoiceItemsListDTO;
    }

    public void setInvoiceItemsDTOList(List<InvoiceItemDTO> invoiceItemsListDTO) {
        if (invoiceItemsListDTO == null) {
            return;
        }
        this.invoiceItemsListDTO = invoiceItemsListDTO;
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

}
