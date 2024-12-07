package com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity.impl;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity.FestivityController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FestivityControllerImpl implements FestivityController {
    private final FestivityUseCasesPort festivityUseCasesPort;


    @Override
    public ResponseEntity<FestivityDTO> createFestivity(FestivityDTO festivityDTO) {
        Festivity festivity = Festivity.builder()
                .name(festivityDTO.getName())
                .startDate(festivityDTO.getStartDate())
                .endDate(festivityDTO.getEndDate())
                .patron(festivityDTO.getPatron())
                .villageId(festivityDTO.getVillageId())
                .build();
        festivityUseCasesPort.createFestivity(festivity);
        return ResponseEntity.ok(festivityDTO);
    }

    @Override
    public ResponseEntity<FestivityDTO> getFestivityByVillageId(String villageId) {
        Festivity festivity = festivityUseCasesPort.getFestivityByVillageId(villageId);
        return ResponseEntity.ok(FestivityDTO.builder()
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .villageId(festivity.getVillageId())
                .build());
    }

}
