package com.Bazaar.Spring_Bazaar.Controller;


import com.Bazaar.Spring_Bazaar.Enum.Category;
import com.Bazaar.Spring_Bazaar.RequestDTO.ProductRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.ProductResponseDto;
import com.Bazaar.Spring_Bazaar.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto;
        try {
           productResponseDto = productService.addProduct(productRequestDto);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{category}")
    public List<ProductResponseDto> getByCategory(@PathVariable("category")Category category){
        return productService.findByCategory(category);
    }
}
