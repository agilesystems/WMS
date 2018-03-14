/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Branch;
import com.xnet.wms.repository.BranchRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Override
    public Branch addNew(Branch branch) {
        if (branch == null) {
            return null;
        }
        return branchRepository.save(branch);
    }

    @Override
    public Collection<Branch> fincAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch findByID(int id) {
        return branchRepository.findOne(id);
    }

}
