package com.Bazaar.Spring_Bazaar.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private String productName;

    private Date orderedDate;

    private int itemPrice;

    private int quantityOrdered;

    private int deliveryCharge;

    private int totalCost;

    private String cardUsedForPayment;
}
