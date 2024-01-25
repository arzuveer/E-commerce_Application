package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Convertor.CardConvertor;
import com.Bazaar.Spring_Bazaar.Exception.CustomerNotFoundException;
import com.Bazaar.Spring_Bazaar.Model.Card;
import com.Bazaar.Spring_Bazaar.Model.Customer;
import com.Bazaar.Spring_Bazaar.Repository.CustomerRepository;
import com.Bazaar.Spring_Bazaar.RequestDTO.CardRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.CardDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
              throw new CustomerNotFoundException("invalid customer Id");
        }
         Card card= CardConvertor.cardRequestDtoToCard(cardRequestDto);
         card.setCustomer(customer);
         customer.getCards().add(card);
         customerRepository.save(customer);
         // coverting card to dto
         CardResponseDto cardResponseDto= new CardResponseDto();
         cardResponseDto.setName(customer.getName());
        // convert every card to carddto
        List<CardDto> cardDtoList= new ArrayList<>();
        for(Card card1: customer.getCards()) {
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

          cardDtoList.add(cardDto);
        }
        cardResponseDto.setCardDtos(cardDtoList);
        return cardResponseDto;
    }
}
