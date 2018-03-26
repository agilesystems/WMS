/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Setting;
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

 
    
    @Override
    public Setting save(Setting setting) {
        
        setting.setId(1);
        
        return settingsRepository.save(setting);
        
    }
    
    @Override
    public Setting find() {
        return settingsRepository.findOne(1);
    }
    
}
