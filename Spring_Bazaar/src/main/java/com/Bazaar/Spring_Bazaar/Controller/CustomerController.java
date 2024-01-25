package com.Bazaar.Spring_Bazaar.Controller;


import com.Bazaar.Spring_Bazaar.RequestDTO.CustomerRequestDto;
import com.Bazaar.Spring_Bazaar.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        return customerService.addCustomer(customerRequestDto);
    }

    // get by id

    // get by email

    // delete by id

    //update

}
