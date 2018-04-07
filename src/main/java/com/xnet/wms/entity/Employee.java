/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "employee")
@Where(clause = "is_deleted=0")
public class Employee extends BaseEntity implements Serializable {

    String firstName;
    String secondName;
    String lastName;
    @ManyToOne(cascade = CascadeType.ALL)
    Job job;

}
