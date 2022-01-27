package com.example.demo.controller;


import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<User> getUser(){
        return userDAO.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id,Model model){
        try {
        return ResponseEntity.ok(model.addAttribute("users", userDAO.getUserById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка");
        }
    }

    @RequestMapping(
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User addEmployee(@RequestBody User user) {
        return userDAO.addUser(user);
    }


    @RequestMapping(value = "/{id}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User updateEmployee(@RequestBody User user, @PathVariable("id") Long id) {
        return userDAO.updateUser(user,id);
    }



    @RequestMapping(value = "/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("id") Long id) {
        userDAO.deleteUser(id);
    }


}
