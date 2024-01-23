package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Converter.SellerConverter;
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
        Seller seller = SellerConverter.SellerRequestDtoToSeller(sellerRequestDto);

        sellerRepository.save(seller);

        return "congrats you have been registered at Spring_Bazaar!!";
    }
    public List<SellerResponseDto> getAll()
    {
        List<Seller> sellers = sellerRepository.findAll();

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller s: sellers)
        {
            SellerResponseDto sellerResponseDto = SellerConverter.sellerToSellerResponseDto(s);

            sellerResponseDtoList.add(sellerResponseDto);
        }
       return sellerResponseDtoList;
    }

    public SellerResponseDto findByPanNo(String panNo){

       Seller seller= sellerRepository.findByPanNo(panNo);

       return SellerConverter.sellerToSellerResponseDto(seller);
    }
    public String deleteSellerById(int id)
    {
        sellerRepository.deleteById(id);
        return "Seller Deleted Successfully !!";
    }
}
