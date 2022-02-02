package com.binariks.hometask_7.controllers;

import com.binariks.hometask_7.entities.Address;
import com.binariks.hometask_7.entities.User;
import com.binariks.hometask_7.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressControllerTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void getAllAddress() {

        List<Address> addressList = (List<Address>) addressRepository.findAll();

        Assertions.assertThat(addressList.size()).isGreaterThan(0);

    }

    @Test
    void getAddressById() {
        Address address = addressRepository.findById(1L).get();

        Assertions.assertThat(address.getAddress_id()).isEqualTo(1L);
    }

    @Test
    void getAddressFromCity() {
        List<Address> addressList = addressRepository.findByCity("Kyiv");

        Assertions.assertThat(addressList.size()).isGreaterThan(0);
    }

    @Test
    void addAddress() {

        Address address = new Address();
        address.setCity("Lviv");
        address.setState("Lvivska");
        address.setZip(77777);
        address.setApt_number(1);
        address.setName("Saharova");

        addressRepository.save(address);

        Assertions.assertThat(address.getZip()).isEqualTo(77777);

    }

    @Test
    void updateAddress() {

        Address address = addressRepository.findById(1L).get();
        address.setName("Chuprynky");

        Address addressUpd = addressRepository.save(address);
        Assertions.assertThat(addressUpd.getName()).isEqualTo("Chuprynky");

    }

    @Test
    void deleteAddress() {

        Address address = addressRepository.findById(1L).get();
        addressRepository.delete(address);

        Address address1 = null;
        Optional<Address> addressOptional = addressRepository.findById(1L);

        if (addressOptional.isPresent()){
            address1 = addressOptional.get();
        }

        Assertions.assertThat(address1).isNull();

    }
}