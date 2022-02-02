package com.binariks.hometask_7.controllers;

import com.binariks.hometask_7.entities.User;
import com.binariks.hometask_7.repository.AddressRepository;
import com.binariks.hometask_7.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void getUserTest(){
        User user = userRepository.findById(1L).get();

        Assertions.assertThat(user.getUser_id()).isEqualTo(1L);
    }

    @Test
    void getAllUserTest(){
        List<User> userList = userRepository.findAll();

        Assertions.assertThat(userList.size()).isGreaterThan(0);
    }

    @Test
    void canSaveUser() {
        User user = new User();
        user.setName("Dima");
        user.setSurname("Dimka");
        user.setEmail("adad@gmail.com");

        userRepository.save(user);

        Assertions.assertThat(user.getName()).isEqualTo("Dima");

    }

    @Test
    void updateUserTest(){
        User user = userRepository.findById(1L).get();
        user.setName("Dmytro");

        User userUpdated = userRepository.save(user);
        Assertions.assertThat(userUpdated.getName()).isEqualTo("Dmytro");
    }

    @Test
    void deleteUserTest(){
        User user = userRepository.findById(1L).get();
        userRepository.delete(user);

        User user1 = null;
        Optional<User> userOptional = userRepository.findById(1L);

        if (userOptional.isPresent()){
            user1 = userOptional.get();
        }

        Assertions.assertThat(user1).isNull();
    }






}