package com.Bazaar.Spring_Bazaar.Controller;


import com.Bazaar.Spring_Bazaar.RequestDTO.CardRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.CardResponseDto;
import com.Bazaar.Spring_Bazaar.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        CardResponseDto cardResponseDto;
        try{
            cardResponseDto=cardService.addCard(cardRequestDto);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }

    //remove
    // remove all card for customer

}
