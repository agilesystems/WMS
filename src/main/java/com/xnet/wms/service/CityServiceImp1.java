/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Category;
import com.xnet.wms.entity.City;
import com.xnet.wms.repository.CategoryRepository;
import com.xnet.wms.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class CityServiceImp1 implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findById(int id) {
        return cityRepository.findOne(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
