package com.binariks.hometask_7.repository;

import com.binariks.hometask_7.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
