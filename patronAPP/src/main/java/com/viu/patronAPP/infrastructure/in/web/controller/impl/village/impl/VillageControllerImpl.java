package com.viu.patronAPP.infrastructure.in.web.controller.impl.village.impl;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.village.VillageController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VillageControllerImpl implements VillageController {

    private final VillageUseCasesPort villageUseCasesPort;

    @Override
    public ResponseEntity<VillageDTO> getVillageById(String villageId) {
        Village village = villageUseCasesPort.getVillageById(villageId);
        return ResponseEntity.ok(VillageDTO.builder().id(village.getId()).provinceId(village.getProvinceId()).name(village.getName()).coords(village.getCoords()).imageUrl(village.getImageUrl())
                .festivity(FestivityDTO.builder().id(village.getFestivity().getId()).name(village.getFestivity().getName()).startDate(village.getFestivity().getStartDate()).endDate(village.getFestivity().getEndDate()).patron(village.getFestivity().getPatron()).villageId(village.getFestivity().getVillageId()).build()).build());
    }
}
