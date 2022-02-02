package com.binariks.hometask_7.repository;

import com.binariks.hometask_7.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {


//    @Query(value = "from Address where Address.city = :city")
//    List<Address> findById(Long id);

    List<Address> findByCity(String city);

    //List<Address> findAll();



}
