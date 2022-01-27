package com.example.demo.repository;


import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Transactional
    @Modifying
    @Query("update Book u set u.name = :name where u.id = :id")
    void updateBook(@Param(value = "id") long id, @Param(value = "name") String name);

}
