package com.Bazaar.Spring_Bazaar.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDto {

    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String panNo;
}
