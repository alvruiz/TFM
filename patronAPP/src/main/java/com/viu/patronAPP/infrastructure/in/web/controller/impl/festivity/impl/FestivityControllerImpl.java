package com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity.impl;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity.FestivityController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class FestivityControllerImpl implements FestivityController {
    private final FestivityUseCasesPort festivityUseCasesPort;
    private final VillageUseCasesPort villageUseCasesPort;

    @Override
    public ResponseEntity<FestivityDTO> createFestivity(FestivityDTO festivityDTO) {
        log.info("Create festivity: {}", festivityDTO);
        Festivity festivity = Festivity.builder()
                .name(festivityDTO.getName())
                .startDate(festivityDTO.getStartDate())
                .endDate(festivityDTO.getEndDate())
                .patron(festivityDTO.getPatron())
                .build();
        festivityUseCasesPort.createFestivity(festivity);
        return ResponseEntity.ok(festivityDTO);
    }

    @Override
    public ResponseEntity<FestivityDTO> getFestivityByVillageId(String villageId) {
        log.info("Get festivity by village id: {}", villageId);
        Festivity festivity = festivityUseCasesPort.getFestivityByVillageId(villageId);
        Village village = villageUseCasesPort.getVillageById(villageId);
        return ResponseEntity.ok(FestivityDTO.builder()
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .villageId(village.getId())
                .build());
    }

    @Override
    public ResponseEntity<List<FestivityDTO>> getFestivities(String page, String size) {
        log.info("Get festivities page: {} size: {}", page, size);
        List<Festivity> festivities = festivityUseCasesPort.getAllFestivities(page, size);
        return ResponseEntity.ok(festivities.stream().map(festivity -> FestivityDTO.builder()
                .id(festivity.getId())
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .villageId(villageUseCasesPort.getVillageByFestivity(festivity).getId())
                .build()).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<FestivityDTO> getFestivityById(String festivityId) {
        log.info("Get festivity by id: {}", festivityId);
        Festivity festivity = festivityUseCasesPort.getById(festivityId);
        return ResponseEntity.ok(FestivityDTO.builder()
                .id(festivity.getId())
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .villageId(villageUseCasesPort.getVillageByFestivity(festivity).getId())
                .build());
    }
}
