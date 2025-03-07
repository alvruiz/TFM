package com.viu.patronAPP.infrastructure.DTO.province;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinceDTO {
    private String id;
    private String name;
    private Coords coords;
    private String image;
    private String autonomousCommunity;
}
