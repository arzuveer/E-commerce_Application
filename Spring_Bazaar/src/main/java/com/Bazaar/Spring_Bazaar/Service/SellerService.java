package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Convertor.SellerConvertor;
import com.Bazaar.Spring_Bazaar.Model.Seller;
import com.Bazaar.Spring_Bazaar.Repository.SellerRepository;
import com.Bazaar.Spring_Bazaar.RequestDTO.SellerRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public String addSeller(SellerRequestDto sellerRequestDto)
    {
        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);

        sellerRepository.save(seller);

        return "congrats you have been registered at Spring_Bazaar!!";
    }
    public List<SellerResponseDto> getAll()
    {
        List<Seller> sellers = sellerRepository.findAll();

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller s: sellers)
        {
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(s);

            sellerResponseDtoList.add(sellerResponseDto);
        }
       return sellerResponseDtoList;
    }

    public SellerResponseDto findByPanNo(String panNo){

       Seller seller= sellerRepository.findByPanNo(panNo);

       return SellerConvertor.sellerToSellerResponseDto(seller);
    }
    public String deleteSellerById(int id)
    {
        sellerRepository.deleteById(id);
        return "Seller Deleted Successfully !!";
    }
    public List<SellerResponseDto> findByAge(int age){
        List<Seller> sellers = sellerRepository.findByAge(age);

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller s: sellers)
        {
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(s);

            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;

    }
}
