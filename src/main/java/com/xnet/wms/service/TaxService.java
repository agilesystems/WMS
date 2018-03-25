/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Tax;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface TaxService {

    Tax save(Tax tax);

    Tax findById(int id);

    List<Tax> findAll();
}
