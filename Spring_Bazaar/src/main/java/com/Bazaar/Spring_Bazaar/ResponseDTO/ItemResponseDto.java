package com.Bazaar.Spring_Bazaar.ResponseDTO;

import com.Bazaar.Spring_Bazaar.Enum.Category;
import com.Bazaar.Spring_Bazaar.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {

    private String name;
    private  int price;
    private int quantity;
    private Category category;
    private ProductStatus productStatus;
}
