package com.viu.patronAPP.infrastructure.DTO.province;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class VillageDTO {
    private String id;
    private String name;
    private Coords coords;
    private String imageUrl;
    private String provinceId;
    private FestivityDTO festivity;
}
