/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "city")
public class City implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private State state;
    
    public City() {
    }

    public City(int id , String name) {
        setId(id);
        setName(name);
    }

    public City(String name, State state) {
        
        setName(name);
        setState_(state);
        
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
    
    public State getState() {
        return state;
    }
    
    public void setState_(State state) {
        this.state = state;
    }
    
}
