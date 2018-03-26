/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xnet.wms.service.SettingService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    @GetMapping("")
    Setting getSetting() {
        return settingService.find();
    }

    @PostMapping("")
    Setting addNew(@RequestBody Setting setting, HttpServletRequest httpServletRequest) {

        return settingService.save(setting);
    }
}
