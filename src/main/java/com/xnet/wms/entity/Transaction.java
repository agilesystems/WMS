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
@Entity(name = "transaction")
public class Transaction {
    
    @Id
    int id;
    String name;
}
