/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.City;
import com.xnet.wms.entity.Country;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface CityService {

    City save(City city);

    City findById(int id);

    List<City> findAll();
}
