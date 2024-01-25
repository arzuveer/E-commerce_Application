package com.Bazaar.Spring_Bazaar.Convertor;

import com.Bazaar.Spring_Bazaar.Model.Customer;
import com.Bazaar.Spring_Bazaar.RequestDTO.CustomerRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
    }

}
