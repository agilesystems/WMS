/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Country;
import com.xnet.wms.entity.State;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface StateService {

    State save(State state);

    State findById(int id);

    List<State> findAll();
}
