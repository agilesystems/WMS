/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Country;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface CountryService {

    Country save(Country country);

    Country findById(int id);

    List<Country> findAll();
}
