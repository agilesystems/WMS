/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.SystemException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
public interface SystemExceptionService {

    void addNew(SystemException systemException);
}
