package com.viu.patronAPP.infrastructure.DTO.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinceDTO {
    private String name;
    private String coords;
    private String image;
}
