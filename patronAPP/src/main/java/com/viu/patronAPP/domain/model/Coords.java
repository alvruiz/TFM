package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Coords {
    private double latitude;
    private double longitude;
}
