/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Country;
import com.xnet.wms.repository.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class CountryServiceImp implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findById(int id) {
        return countryRepository.findOne(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

}
