package com.Bazaar.Spring_Bazaar.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    private String name;
    private int age;
    private  String mobNo;
    private  String email;
}
