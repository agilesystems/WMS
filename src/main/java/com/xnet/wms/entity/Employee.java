/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author ramy
 *
 */
@Entity(name = "employee")
public class Employee extends BaseEntity implements Serializable {

    String firstName;
    String secondName;
    String lastName;
    @ManyToOne(cascade = CascadeType.ALL)
    Job job;

}
