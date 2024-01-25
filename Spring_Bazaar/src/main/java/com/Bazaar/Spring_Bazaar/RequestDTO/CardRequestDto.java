package com.Bazaar.Spring_Bazaar.RequestDTO;


import com.Bazaar.Spring_Bazaar.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDto {

    private CardType cardType;
    private  String cardNo;
    private int cvv;
    private  int customerId;
}
