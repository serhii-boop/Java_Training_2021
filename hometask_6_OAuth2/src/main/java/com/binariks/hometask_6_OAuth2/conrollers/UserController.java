package com.binariks.hometask_6_OAuth2.conrollers;


import com.binariks.hometask_6_OAuth2.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1L, "Serhii", "Dutchyn"),
            new User(2L, "Dima", "Dmytro"),
            new User(3L, "Vasya", "Vasyl")
    ));


    @GetMapping
    public List<User> getAll(){
        return userList;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public User create(@RequestBody User user){
        this.userList.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.userList.removeIf(user -> user.getId().equals(id));
    }

}
