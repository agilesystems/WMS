/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.CityDTO;
import com.xnet.wms.entity.City;
import com.xnet.wms.service.CityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/all")
    List<CityDTO> getAll() {
        List<CityDTO> cities = new ArrayList<>();
        cityService.findAll().forEach(city -> {
            cities.add(new CityDTO(city));
        });
        return cities;
    }

    @GetMapping("/{id}")
    CityDTO getById(@PathVariable("id") int id) {
        return new CityDTO(cityService.findById(id));
    }
}
