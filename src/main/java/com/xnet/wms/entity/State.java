/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author ramy
 */
@Entity(name = "state")
public class State implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Country country;
    @OneToMany(mappedBy = "state")
    private List<City> citiesList;

    public State() {
    }
    public State(int id, String name , Country country) {
        setId(id);
        setName(name);
        setCountry(country);
    }

    public State(String name, Country country) {
        setName(name);
        setCountry(country);
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<City> citiesList) {
        this.citiesList = citiesList;
    }

}
