package com.Bazaar.Spring_Bazaar.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    private  int customerId;
    private int productId;
    private int requiredQuantity;
}
