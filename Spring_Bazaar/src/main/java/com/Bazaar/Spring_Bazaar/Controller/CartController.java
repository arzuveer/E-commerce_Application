package com.Bazaar.Spring_Bazaar.Controller;

import com.Bazaar.Spring_Bazaar.Model.Customer;
import com.Bazaar.Spring_Bazaar.RequestDTO.OrderRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.OrderResponseDto;
import com.Bazaar.Spring_Bazaar.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody OrderRequestDto orderRequestDto){

        String response = "";
        try {
            response = cartService.addToCart(orderRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return response;
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId){
       List<OrderResponseDto> orderResponseDtos;
       try{
           orderResponseDtos=cartService.checkoutCart(customerId);
        }
       catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity(orderResponseDtos, HttpStatus.ACCEPTED);
    }

}
