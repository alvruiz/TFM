package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Province {
    private String id;
    private String name;
    private Coords coords;
    private String image;
}
