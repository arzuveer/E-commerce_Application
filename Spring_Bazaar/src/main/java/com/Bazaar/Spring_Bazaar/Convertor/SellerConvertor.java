package com.Bazaar.Spring_Bazaar.Convertor;


import com.Bazaar.Spring_Bazaar.Model.Seller;
import com.Bazaar.Spring_Bazaar.RequestDTO.SellerRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.SellerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {

    // all func will be static here no parameters no objects

    public Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .email(sellerRequestDto.getEmail())
                .build();// to build object and set it

    }
    public SellerResponseDto sellerToSellerResponseDto(Seller seller)
    {
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .email(seller.getEmail())
                .build();
    }
}
