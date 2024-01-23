package com.Bazaar.Spring_Bazaar.RequestDTO;

import com.Bazaar.Spring_Bazaar.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    private String name;
    private int price;
    private int quantity;
    private Category category;
    private int sellerId;
}
