
package com.xnet.wms.controller;

import com.xnet.wms.entity.Category;
import com.xnet.wms.service.CategoryService;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mhdsy
 */
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    Collection<Category> getAll(HttpServletRequest httpServletRequest) { 
        return categoryService.findAll();
    }

}