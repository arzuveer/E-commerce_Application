package com.Bazaar.Spring_Bazaar.ResponseDTO;


import com.Bazaar.Spring_Bazaar.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private String name;
    private int price;
    private int quantity;
    private ProductStatus productStatus;
}
