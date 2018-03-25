/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Category;
import com.xnet.wms.entity.State;
import com.xnet.wms.repository.CategoryRepository;
import com.xnet.wms.repository.StateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class StateServiceImp1 implements StateService {

    @Autowired
    StateRepository stateRepository;

    @Override
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State findById(int id) {
        return stateRepository.findOne(id);
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

}
