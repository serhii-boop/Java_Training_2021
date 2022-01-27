package com.example.demo.repository;

import com.example.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "from Author a where a.birth_date BETWEEN :startDate AND :endDate")
    public List<Author> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

}
