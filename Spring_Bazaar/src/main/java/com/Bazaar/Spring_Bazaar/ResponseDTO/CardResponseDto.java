package com.Bazaar.Spring_Bazaar.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDto {

    private String Name;

    private List<CardDto> cardDtos;
}
