package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Convertor.ProductConvertor;
import com.Bazaar.Spring_Bazaar.Enum.Category;
import com.Bazaar.Spring_Bazaar.Exception.SellerNotFoundException;
import com.Bazaar.Spring_Bazaar.Model.Product;
import com.Bazaar.Spring_Bazaar.Model.Seller;
import com.Bazaar.Spring_Bazaar.Repository.ProductRepository;
import com.Bazaar.Spring_Bazaar.Repository.SellerRepository;
import com.Bazaar.Spring_Bazaar.RequestDTO.ProductRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller;
        // to check if that seller is in db or not
        try{
           seller= sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e)
        {
            throw new SellerNotFoundException("invalid seller Id");
        }

        Product product= ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);// adding product to list in seller table

        sellerRepository.save(seller);// it will cascade on product also

        ProductResponseDto productResponseDto= ProductConvertor.productToProductResponseDto(product);

        return productResponseDto;

    }
    public List<ProductResponseDto> findByCategory(Category category){
        List<Product> products= productRepository.findAllByCategory(category);

        List<ProductResponseDto> productResponseDtos= new ArrayList<>();
        for(Product product: products)
        {
            ProductResponseDto product1=ProductConvertor.productToProductResponseDto(product);

            productResponseDtos.add(product1);
        }
        return productResponseDtos;
    }
}
