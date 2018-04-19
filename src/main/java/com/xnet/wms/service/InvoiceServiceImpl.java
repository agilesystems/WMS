/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Invoice;
import com.xnet.wms.entity.InvoiceItem;
import com.xnet.wms.entity.StoreItem;
import com.xnet.wms.exception.GlobalControllerAdvice;
import com.xnet.wms.helper.Global;
import com.xnet.wms.repository.InvoiceRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ramy
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    StoreItemService storeItemService;

    @Autowired
    InvoiceTypeService invoiceTypeService;

    @Autowired
    AccountService accountService;
    @Autowired
    InvoiceItemService invoiceItem;

    @Override
//    @Transactional
    public Invoice addSellInvoice(Invoice invoice) {
        invoice.setInvoiceType(invoiceTypeService.findByID(Global.INVOICE_TYPE_SELL));

        if (!isValidInvoice(invoice)) {
            return null;
        }

        List<InvoiceItem> items = new ArrayList<>();
        invoice.getInvoiceItemsList().forEach(i -> {
            i.setInvoice(invoice);

            if (items.isEmpty()) {
                items.add(i);
            } else {
                items.forEach(it -> {
                    if (it.getId() == i.getId()) {
                        it.setQuantity(it.getQuantity() + i.getQuantity());
                    } else {
                        items.add(i);
                    }
                });
            }
             invoiceItem.save(i);
        });
        invoice.setInvoiceItemsList(items);
        invoiceRepository.save(invoice);


        /*
        update Store items quantiy after making new invoice
         */
        items.forEach(i -> {
            StoreItem storeItem = storeItemService.findById(i.getId());
            if ((storeItem.getAvailableQuantity() - i.getQuantity()) >= 0) {
                storeItem.setAvailableQuantity(storeItem.getAvailableQuantity() - i.getQuantity());
                storeItemService.save(storeItem);
            }
        }
        );

        return invoice;
    }

    @Override
    public boolean isValidInvoice(Invoice invoice) {

        if (invoice == null) {
            log.warn("Invoice is null");
            return false;
        }
        if (invoice.getAccount() == null || invoice.getAccount().getId() <= 0) {
            log.warn("Invoice.Account is not Valid");
            return false;
        }
        invoice.setAccount(accountService.findById(invoice.getAccount().getId()));
        if (invoice.getBranch() == null || invoice.getBranch().getId() <= 0) {
            log.warn("Invoice.Branch is not Valid");
            return false;
        }
        if (invoice.getInvoiceDate() == null) {
            log.warn("invoice.Date is null");
            return false;
        }

        if (invoice.getInvoiceItemsList() == null || invoice.getInvoiceItemsList().isEmpty()) {
            log.warn("Invoice Items is Empty");
            return false;
        }
        if (invoice.getPaymentType() == null || invoice.getPaymentType().getId() <= 0) {
            log.warn("Invoice.PaymentType is not valid");
            return false;
        }
        if ((invoice.getAccount().getAccountType().getId() == Global.ACCOUNT_TYPE_CUSTOMER
                && !(invoice.getInvoiceType().getId() == Global.INVOICE_TYPE_SELL
                || invoice.getInvoiceType().getId() == Global.INVOICE_TYPE_REFUND_SELL)) || (invoice.getAccount().getAccountType().getId() == Global.ACCOUNT_TYPE_SUPPLIER
                && !(invoice.getInvoiceType().getId() == Global.INVOICE_TYPE_BUY
                || invoice.getInvoiceType().getId() == Global.INVOICE_TYPE_REFUND_BUY))) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Invoice invoice) {
        invoice.setIsDeleted(true);
        invoice.setDeletedDate(new Date());
        invoiceRepository.save(invoice);
        return true;
    }

    @Override
    public Invoice findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Invoice> findByAccountId(int accountid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invoice findByRefrence(String refrence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Invoice> findByTypeid(int typeid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void invoicetotal(InvoiceItem invoiceitem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void invoicediscount(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void caltax1(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void caltax2(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void caltax3(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void outstanding(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void netvalue(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

}
