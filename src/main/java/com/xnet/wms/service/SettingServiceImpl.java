/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Setting;
import com.xnet.wms.entity.Tax;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xnet.wms.repository.SettingRepository;

/**
 *
 * @author ramy
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingRepository settingsRepository;
    @Autowired
    TaxService taxService;

    @Override
    public Setting save(Setting setting) {

        List<Tax> taxes = setting.getTaxesList();
        setting.setId(1);

        setting.setTaxesList(null);
        Setting set = settingsRepository.saveAndFlush(setting);
        taxes.forEach(tax -> {
            tax.setSettingId(set.getId());
            taxService.save(tax);
        });
        return find();

    }

    @Override
    public Setting find() {
        return settingsRepository.findOne(1);
    }

}
