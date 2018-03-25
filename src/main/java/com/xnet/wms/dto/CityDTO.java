/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.City;

/**
 *
 * @author ramy
 */
public class CityDTO {

    private int id;
    private String name;
    private String stateName;
    private int stateId;
    private String countryName;
    private int countryId;

    public CityDTO() {
    }

    public CityDTO(City city) {
        if (city == null) {
            return;
        }
        this.id = city.getId();
        this.name = city.getName();
        this.stateName = city.getState().getName();
        this.stateId = city.getState().getId();
        this.countryName = city.getState().getCountry().getName();
        this.countryId = city.getState().getCountry().getId();
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

}
