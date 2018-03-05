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
@Entity(name = "store")
public class Store {

    @OneToMany(mappedBy = "store")
    private List<StoreItem> storeItemsList;
    @Id
    Integer id;
    String name;
    String description;
    String note;
          
}
