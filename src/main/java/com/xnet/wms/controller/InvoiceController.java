/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.InvoiceDTO;
import com.xnet.wms.entity.Invoice;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.InvoiceService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
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
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    UserService userService;

    @PostMapping("/sell/add")
    public int addSellNew(@RequestBody Invoice invoice, HttpServletRequest httpServletRequest) {
//        invoice.setCreatedBy((User) ((Claims) httpServletRequest.getAttribute("claims")).get("user"));
//        invoice.setBranch(invoice.getCreatedBy().getBranch());

        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        invoice.setCreatedBy(currentUser);
        invoice.setBranch(currentUser.getBranch());

        return invoiceService.addSellInvoice(invoice).getId();
    }

    @PostMapping("/buy/add")
    public int addBuyNew(@RequestBody Invoice invoice, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        invoice.setCreatedBy(currentUser);
        invoice.setBranch(currentUser.getBranch());
        return invoiceService.addBuyInvoice(invoice).getId();
    }
    
    @PostMapping("/refundSell/add")
    public int addRefundSellNew(@RequestBody Invoice invoice, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        invoice.setCreatedBy(currentUser);
        invoice.setBranch(currentUser.getBranch());
        return invoiceService.addRefundSellInvoice(invoice).getId();
    }
@PostMapping("/refundBuy/add")
    public int addRefundBuyNew(@RequestBody Invoice invoice, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        invoice.setCreatedBy(currentUser);
        invoice.setBranch(currentUser.getBranch());
        return invoiceService.addBuyInvoice(invoice).getId();
    }
    @GetMapping("/id/{id}")
    InvoiceDTO getOneById(@PathVariable("id") int id, HttpServletRequest httpServletRequest) {
        return new InvoiceDTO(invoiceService.findById(id));
    }

    @GetMapping("/all")
    Collection<InvoiceDTO> getAll(HttpServletRequest httpServletRequest) {

        Collection<InvoiceDTO> invoices = new ArrayList<>();
        invoiceService.findAll().forEach(i -> {
            invoices.add(new InvoiceDTO(i));
        });
        return invoices;
    }
}
