package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Convertor.CustomerConvertor;
import com.Bazaar.Spring_Bazaar.Model.Cart;
import com.Bazaar.Spring_Bazaar.Model.Customer;
import com.Bazaar.Spring_Bazaar.Repository.CustomerRepository;
import com.Bazaar.Spring_Bazaar.RequestDTO.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer= CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);
       // allocate cart to customer
        Cart cart= new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        customerRepository.save(customer);

        return "welcome! to Spring_Bazaar, happy shopping!";
    }
}
