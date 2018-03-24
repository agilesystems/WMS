/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.SystemException;
import com.xnet.wms.repository.SystemExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class SystemExceptionServiceImp implements SystemExceptionService {

    @Autowired
    SystemExceptionRepository exceptionRepository;

    @Override
    public void addNew(SystemException systemException) {
        exceptionRepository.save(systemException);
    }

}
