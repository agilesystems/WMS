/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ramy
 */
@Entity(name = "invice_type")
public class InvoiceType {
    
    @Id
    Integer id;
    String name;
}
