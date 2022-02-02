package com.binariks.hometask_7.intagretionTestForController;

import com.binariks.hometask_7.entities.Address;
import com.binariks.hometask_7.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class )
@SpringBootTest
@WebAppConfiguration
class AddressControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    void returnsAllAddress() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/address")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].address_id").isNotEmpty());
    }

    @Test
    void returnsAddressById() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                .get("/address/id/{id}", 2)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address_id").value(2));
    }

    @Test
    void returnsAddressByCity() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                .get("/address/city/{city}", "Lviv")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].city").isNotEmpty());
    }


    @Test
    public void createAddress() throws Exception
    {
        Address address = new Address();
        address.setCity("Chervonohrad");
        address.setState("Lvivska");
        address.setZip(10101);
        address.setApt_number(76);
        address.setName("Mudroho");
        mvc.perform( MockMvcRequestBuilders
                .post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mudroho"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void updateAddress() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/address/update/{id}", 2)
                .content(asJsonString(new Address("Pohivka", "Ivano-franlivska", 87875, 7, "melnyks")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Pohivka"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("Ivano-franlivska"));
    }

    @Test
    public void deleteAddress() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/address/delete/{id}", 3) )
                .andExpect(status().isOk());
    }




}