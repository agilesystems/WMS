/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.InvoiceDTO;
import com.xnet.wms.entity.Invoice;
import com.xnet.wms.service.InvoiceService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/add")
    InvoiceDTO addNew(@RequestBody Invoice invoice) {

        return new InvoiceDTO(invoiceService.addNew(invoice));
    }

    @GetMapping("/id/{id}")
    InvoiceDTO getOneById(@PathVariable("id") int id) {
        return new InvoiceDTO(invoiceService.findById(id));
    }

    @GetMapping("/all")
    Collection<InvoiceDTO> getAll() {
        Collection<InvoiceDTO> invoices = new ArrayList<>();
        invoiceService.findAll().forEach(i -> {
            invoices.add(new InvoiceDTO(i));
        });
        return invoices;
    }
}