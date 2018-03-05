/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "country")
public class Country {

    @OneToMany(mappedBy = "country")
    private List<State> statesList;    
    @Id
    Integer id;
    String name;
}
