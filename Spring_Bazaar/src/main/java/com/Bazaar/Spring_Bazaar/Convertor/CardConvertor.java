package com.Bazaar.Spring_Bazaar.Convertor;

import com.Bazaar.Spring_Bazaar.Model.Card;
import com.Bazaar.Spring_Bazaar.RequestDTO.CardRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.CardResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConvertor {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
       return Card.builder()
               .cardNo(cardRequestDto.getCardNo())
               .cardType(cardRequestDto.getCardType())
               .cvv(cardRequestDto.getCvv())
               .build();
    }

}
