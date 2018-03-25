/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Invoice;
import com.xnet.wms.entity.InvoiceItem;
import com.xnet.wms.entity.Item;
import com.xnet.wms.entity.Store;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
public interface InvoiceService {

    Invoice save(Invoice invoice);

    /**
     * This method for soft deleted the invoice , just update the deleted field
     * with value(1) and update deleted field with value (1) in the invoice
     * items . restore the discounted quantity to the store by item id
     *
     * @param invoice an argument specify to update invoice
     * @return true if the invoice updated , false if not updated
     */
    boolean delete(Invoice invoice);

    /**
     * Returns Invoice Object by the id of invoice
     *
     * @param id integer to find the invoice by id
     * @return invoice if find by id of invoice
     */
    Invoice findById(int id);

    /**
     * This Method th get all invoices that concern the account id
     *
     * @param accountid integer parameter to dedicate the invoices by account id
     * @return A Collection of invoice .
     */
    Collection<Invoice> findByAccountId(int accountid);

    /**
     * Returns invoice object by reference in the store documents
     *
     * @param refrence A string parameter to dedicate the reference of invoice
     * in the store documents .
     * @return
     */
    Invoice findByRefrence(String refrence);

    /**
     * This Method to get A collection of invoices by the type of invoice (sales
     * - purchase - returned sales - Returned purchase )
     *
     * @param typeid an integer parameter to dedicate the type of invoice
     * @return A collection data of invoices where indicate the type of invoice
     * F
     */
    Collection<Invoice> findByTypeid(int typeid);

    /**
     * this void method to calculate the total of invoice
     *
     * @param invoiceitem an Argument to get the invoice total from invoice
     * items (quantity * price) to all items and update the field of total
     */
    void invoicetotal(InvoiceItem invoiceitem);

    /**
     * Calculate the discount of invoice to the customer
     *
     * @param invoice an Argument to get the total from invoice and calculate
     * the discount update the discount field in the invoice table
     */
    void invoicediscount(Invoice invoice);

    /**
     * Calculate the taxes of invoice
     *
     * @param invoice an Argument to get the total and the percentage of tax to
     * calculate invoice tax update the field of tax value
     */
    void caltax1(Invoice invoice);

    void caltax2(Invoice invoice);

    void caltax3(Invoice invoice);

    /**
     * calculate the outstanding if invoice by (Total value - Paid value ) and
     * appear in Customer Position
     *
     * @param invoice an Argument to get the invoice total and paid update
     * invoice with the outstanding value
     */
    void outstanding(Invoice invoice);

    /**
     * This Method to calculate the the net value of invoice after discount and
     * taxes . net value = (Total value - discount ) + taxes value
     *
     * @param invoice An invoice parameter that indicate the invoice to
     * calculate the net value.
     */
    void netvalue(Invoice invoice);

    //void updateStore(Invoiceitem invoiceItem);
    Collection<Invoice> findAll();
}
