package com.Bazaar.Spring_Bazaar.Convertor;

import com.Bazaar.Spring_Bazaar.Enum.ProductStatus;
import com.Bazaar.Spring_Bazaar.Model.Product;
import com.Bazaar.Spring_Bazaar.RequestDTO.ProductRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public  static Product productRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product)
    {
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
