/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author ramy
 */
@Entity(name = "country")
public class Country {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "country")
    private List<State> statesList;

    public Country(String name) {

        setName(name);
    }

    public Country() {
    }

    public Country(int id , String name) {
        setId(id);
        setName(name);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<State> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<State> statesList) {
        this.statesList = statesList;
    }

}
