/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.InvoiceType;
import com.xnet.wms.service.InvoiceTypeService;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("invoice-type")
public class InvoiceTypeController {

    @Autowired
    InvoiceTypeService invoiceTypeService;

    @PostMapping("/add")
    InvoiceType addNew(@RequestBody InvoiceType invoiceType,HttpServletRequest httpServletRequest) {

        return invoiceTypeService.addNew(invoiceType);

    }

    @GetMapping("/all")
    Collection<InvoiceType> getAll(HttpServletRequest httpServletRequest) {
        return invoiceTypeService.findAll();
    }

    @GetMapping("/id/{id}")
    InvoiceType getOneByID(@PathVariable("id") int id,HttpServletRequest httpServletRequest) {
        return invoiceTypeService.findByID(id);
    }

}
