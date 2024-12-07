package com.viu.patronAPP.infrastructure.DTO.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class FestivityDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String patron;
    private String villageId;
}
