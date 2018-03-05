/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "state")
public class State implements Serializable {

    @OneToMany(mappedBy = "state")
    private List<City> citiesList;

    @Id
    Integer id;
    String name;
    @ManyToOne(cascade = CascadeType.ALL)
    Country country;
}
