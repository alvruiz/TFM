package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Village {
    private String id;
    private String name;
    private Coords coords;
    private String imageUrl;
    private String provinceId;
    private Festivity festivity;
}
