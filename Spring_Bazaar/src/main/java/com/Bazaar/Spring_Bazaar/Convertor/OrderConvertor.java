package com.Bazaar.Spring_Bazaar.Convertor;

import com.Bazaar.Spring_Bazaar.Model.Ordered;
import com.Bazaar.Spring_Bazaar.Model.Product;
import com.Bazaar.Spring_Bazaar.ResponseDTO.OrderResponseDto;
import lombok.experimental.UtilityClass;


@UtilityClass
public class OrderConvertor {

    public static OrderResponseDto orderToOrderResponseDto(Ordered ordered, Product product){
        return OrderResponseDto.builder()
                .productName(product.getName())
                .deliveryCharge(ordered.getDeliveryCharge())
                .orderedDate(ordered.getOrderedDate())
                .totalCost(ordered.getTotalCost())
                .itemPrice(product.getPrice())
                .cardUsedForPayment(ordered.getCardUsedForPayment())
                .build();
    }

}
