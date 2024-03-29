package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Convertor.ItemConvertor;
import com.Bazaar.Spring_Bazaar.Exception.ProductNotFoundException;
import com.Bazaar.Spring_Bazaar.Model.Item;
import com.Bazaar.Spring_Bazaar.Model.Product;
import com.Bazaar.Spring_Bazaar.Repository.ItemRepository;
import com.Bazaar.Spring_Bazaar.Repository.ProductRepository;
import com.Bazaar.Spring_Bazaar.ResponseDTO.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {
        Product product;
        try{
            product= productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("sorry!,invalid product id");
        }

        Item item= Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        // u have to map item to product like this product has now become item
        product.getItems().add(item);
        productRepository.save(product);

        // prepare dto
        ItemResponseDto itemResponseDto= ItemResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}
