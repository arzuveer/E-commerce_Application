package com.Bazaar.Spring_Bazaar.Controller;

import com.Bazaar.Spring_Bazaar.RequestDTO.OrderRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.OrderResponseDto;
import com.Bazaar.Spring_Bazaar.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place_order") // direct order
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
      OrderResponseDto orderResponseDto;
      try{
          orderResponseDto= orderService.placeOrder(orderRequestDto);
      }
      catch (Exception e){
          return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity(orderResponseDto,HttpStatus.ACCEPTED);
    }
}
