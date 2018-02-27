/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.UserDTO;
import com.xnet.wms.entity.Branch;
import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.MenuService;
import com.xnet.wms.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Muhammad
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {

//        if (userService.save( user.getId(), user.getBranchId(), user.getAddress(), user.getFirstname(), user.getLastname(), user.getPassword(), user.getPhone(), user.getType(), user.getUsername(), user.getEmployeeId() )) {
        if (userService.save(user)) {
            return "User Added Successfully";

        } else {
            return "Wrong to add user";
        }

    }
/**
//    @GetMapping("/allusers")
//    public Collection<UserDTO> getAll() {
//
//        Collection<UserDTO> getUsers = new ArrayList<>();
//
//        for (User user : userService.findAll()) {
//            getUsers.add(new UserDTO(user.getId(),
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getAddress(),
//                    user.getFirstname(),
//                    user.getLastname(),
//                    user.getPhone(),
//                    user.getType(),
//                    user.getBranchId()
//            ));
//        }
//        return getUsers;
//    }

//    @GetMapping("/getByuserAndBranch")
//    public String findByUsernameAndBranchid(@PathParam("username") String username, @PathParam("branchid") Integer branchid) {
//        if (userService.findByUsernameAndBranchid(username, branchid) != null) {
//            return "Already Exist";
//        } else {
//            return "Not Found";
//        }
//
//    }

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable("id") Integer id) {

        User u = userService.getById(id);
        UserDTO dto = new UserDTO(u);
        return dto;
    }
    * **/
}
