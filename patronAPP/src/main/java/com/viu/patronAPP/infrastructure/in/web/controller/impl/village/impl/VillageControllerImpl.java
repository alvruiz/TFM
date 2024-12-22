package com.viu.patronAPP.infrastructure.in.web.controller.impl.village.impl;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.village.VillageController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class VillageControllerImpl implements VillageController {

    private final VillageUseCasesPort villageUseCasesPort;

    @Override
    public ResponseEntity<VillageDTO> getVillageById(String villageId) {
        log.info("Get village by id: {}", villageId);
        Village village = villageUseCasesPort.getVillageById(villageId);
        return ResponseEntity.ok(VillageDTO.builder().id(village.getId()).provinceId(village.getProvince().getId()).name(village.getName()).coords(village.getCoords()).imageUrl(village.getImageUrl())
                .festivity(FestivityDTO.builder().id(village.getFestivity().getId()).name(village.getFestivity().getName()).startDate(village.getFestivity().getStartDate()).endDate(village.getFestivity().getEndDate()).patron(village.getFestivity().getPatron()).villageId(villageId).build()).build());
    }
}
