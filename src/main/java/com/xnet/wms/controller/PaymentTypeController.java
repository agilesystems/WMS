/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.PaymentType;
import com.xnet.wms.service.PaymentTypeService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/payment-type")
public class PaymentTypeController {

    @Autowired
    PaymentTypeService paymentTypeService;

    @GetMapping("/all")
    Collection<PaymentType> getAll(ServletServerHttpRequest httpRequest) {

        return paymentTypeService.findAll();
    }

}
