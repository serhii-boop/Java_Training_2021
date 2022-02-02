package com.binariks.hometask_7.controllers;


import com.binariks.hometask_7.entities.Address;
import com.binariks.hometask_7.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping()
    public List<Address> getAllAddress(){
        return (List<Address>) addressRepository.findAll();
    }
    @GetMapping("/id/{id}")
    public EntityModel<Address> getAddressById(@PathVariable("id") Long id){

        Address address = addressRepository.findById(id).orElse(null);

        EntityModel<Address> resource = EntityModel.of(address);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllAddress())
                .withRel("getAllAddress"));

        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .updateAddress(address,address.getAddress_id()))
                .withRel("update"));

        return resource;
    }

    @GetMapping("/city/{city}")
    public List<Address> getAddressFromCity(@PathVariable("city") String city){
        return addressRepository.findByCity(city);
    }

    @PostMapping("/add")
    public Address addAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return address;
    }

    @PutMapping( "/update/{id}")
    public Address updateAddress(@RequestBody Address newAddress, @PathVariable("id") Long id) {
        return addressRepository.findById(id)
                .map(address -> {
                    address.setCity(newAddress.getCity());
                    address.setState(newAddress.getState());
                    address.setZip(newAddress.getZip());
                    address.setApt_number(newAddress.getApt_number());
                    address.setName(newAddress.getName());
                    return addressRepository.save(address);
                })
                .orElseGet(null);
    }



    @DeleteMapping( "/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        addressRepository.deleteById(id);
    }


}
