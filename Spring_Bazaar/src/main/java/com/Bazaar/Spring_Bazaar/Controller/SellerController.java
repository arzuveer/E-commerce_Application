package com.Bazaar.Spring_Bazaar.Controller;

import com.Bazaar.Spring_Bazaar.RequestDTO.SellerRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.SellerResponseDto;
import com.Bazaar.Spring_Bazaar.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto)
    {
       return sellerService.addSeller(sellerRequestDto);
    }

    @GetMapping("/get_all")
    public List<SellerResponseDto> getAll()
    {
        return sellerService.getAll();
    }

    @GetMapping("/getbypan")
    public SellerResponseDto getByPan(@RequestParam("panNo") String panNo){
       return sellerService.findByPanNo(panNo);
    }

    @DeleteMapping("/delete")
    public String deleteById(int id){
       return sellerService.deleteSellerById(id);
    }
}
