/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.BranchDTO;
import com.xnet.wms.entity.Branch;
import com.xnet.wms.service.BranchService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @GetMapping("/all")
    Collection<BranchDTO> getAll() {

        Collection<BranchDTO> branches = new ArrayList<>();
        branchService.fincAll().forEach(br -> {
            branches.add(new BranchDTO(br));
        });
        return branches;
    }

    @GetMapping("/id/{id}")
    BranchDTO findOneByID(@PathVariable("id") int id) {
        return new BranchDTO(branchService.findByID(id));
    }

    @PostMapping("/add")

    BranchDTO addNew(@RequestBody Branch branch) {

        return new BranchDTO(branchService.addNew(branch));

    }
}
