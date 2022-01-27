package com.example.demo.dao;


import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDAO {
    private static Long USER_COUNT = 0L;

    private List<User> userList = new ArrayList<>();

    {
        userList.add(new User(++USER_COUNT,"Serhii","Dutcyn", "example@gmail.com"));
        userList.add(new User(++USER_COUNT,"Seafafsf","GFGSDGyn", "exFDSHple@gmail.com"));
        userList.add(new User(++USER_COUNT,"Sdfdsssahii","hdsh", "exaF@gmail.com"));
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public User getUserById(Long id){
        return userList.stream().filter(user -> user.getId().equals(id)).findAny().orElse(null);
    }

    public User addUser(User user){
        userList.add(user);
        return user;
    }

    public User updateUser(User user, Long id){
        return userList.set(userList.indexOf(userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null)), user);
    }

    public void deleteUser(Long id) {
        userList.remove(getUserById(id));
    }

}
