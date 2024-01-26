package com.Bazaar.Spring_Bazaar.Convertor;

import com.Bazaar.Spring_Bazaar.Model.Product;
import com.Bazaar.Spring_Bazaar.ResponseDTO.ItemResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConvertor {

    public  static ItemResponseDto productToItemResponseDto(Product product){
        return ItemResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
