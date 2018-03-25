/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Category;
import java.util.List;

/**
 *
 * @author ramy
 */
public interface CategoryService {
    
    Category save(Category category);
    
    Category findById(int id);
    
    List<Category> findAll();
}
