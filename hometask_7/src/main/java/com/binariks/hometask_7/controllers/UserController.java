package com.binariks.hometask_7.controllers;


import com.binariks.hometask_7.entities.Address;
import com.binariks.hometask_7.entities.User;
import com.binariks.hometask_7.repository.AddressRepository;
import com.binariks.hometask_7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @RequestMapping(
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<User> getUser(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") Long id){

        User user = userRepository.findById(id).orElse(null);

        EntityModel<User> resource = EntityModel.of(user);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getUser())
                .withRel("getAllUser"));

        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .updateUser(user,user.getUser_id()))
                .withRel("update"));

        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AddressController.class)
        .getAllAddress())
        .withRel("getAllAddress"));

        return resource;
    }

    @PostMapping( "/add")
    public User addUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setEmail(newUser.getEmail());
                    user.setAddressList(newUser.getAddressList());
                    return userRepository.save(user);
                })
                .orElseGet(null);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }



}
