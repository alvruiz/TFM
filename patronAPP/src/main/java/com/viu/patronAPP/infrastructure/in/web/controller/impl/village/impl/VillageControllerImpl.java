package com.viu.patronAPP.infrastructure.in.web.controller.impl.village.impl;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.in.ProvinceUseCasesPort;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.village.VillageController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class VillageControllerImpl implements VillageController {

    private final VillageUseCasesPort villageUseCasesPort;
    private final ProvinceUseCasesPort provinceUseCasesPort;
    private final FestivityUseCasesPort festivityUseCasesPort;


    @Override
    public ResponseEntity<VillageDTO> getVillageById(String villageId) {
        log.info("Get village by id: {}", villageId);
        Village village = villageUseCasesPort.getVillageById(villageId);
        return ResponseEntity.ok(VillageDTO.builder().id(village.getId()).provinceId(village.getProvince().getId()).name(village.getName()).coords(village.getCoords()).imageUrl(village.getImageUrl())
                .festivity(FestivityDTO.builder().id(village.getFestivity().getId()).name(village.getFestivity().getName()).startDate(village.getFestivity().getStartDate()).endDate(village.getFestivity().getEndDate()).patron(village.getFestivity().getPatron()).villageId(villageId).build()).build());
    }

    @Override
    public ResponseEntity<VillageDTO> updateVillage(String villageId, VillageDTO villageDTO) {
        log.info("Update village: {}", villageDTO);
        Village village = Village.builder()
                .id(villageDTO.getId())
                .name(villageDTO.getName())
                .coords(villageDTO.getCoords())
                .imageUrl(villageDTO.getImageUrl())
                .build();
        villageUseCasesPort.updateVillage(villageId, village);
        return ResponseEntity.ok(villageDTO);
    }

    @Override
    public ResponseEntity<List<VillageDTO>> getAllVillages() {
        log.info("Get all villages");
        return ResponseEntity.ok(villageUseCasesPort.getAllVillages().stream().map(village -> VillageDTO.builder().id(village.getId()).provinceId(village.getProvince().getId()).name(village.getName()).coords(village.getCoords()).imageUrl(village.getImageUrl()).festivity(FestivityDTO.builder().id(village.getFestivity().getId()).name(village.getFestivity().getName()).startDate(village.getFestivity().getStartDate()).endDate(village.getFestivity().getEndDate()).patron(village.getFestivity().getPatron()).villageId(village.getId()).build()).build()).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<String> deleteVillage(String villageId) {
        log.info("Delete village: {}", villageId);
        villageUseCasesPort.deleteVillage(villageId);
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public ResponseEntity<VillageDTO> createVillage(VillageDTO villageDTO) {
        log.info("Create village: {}", villageDTO);
        Province province = provinceUseCasesPort.getProvinceById(villageDTO.getProvinceId());
        Festivity festivity = festivityUseCasesPort.createFestivity(Festivity.builder().id(villageDTO.getFestivity().getId()).name(villageDTO.getFestivity().getName()).startDate(villageDTO.getFestivity().getStartDate()).endDate(villageDTO.getFestivity().getEndDate()).patron(villageDTO.getFestivity().getPatron()).events(new ArrayList<>()).build());
        if (province == null) {
            log.info("Province not found for id: {}", villageDTO.getProvinceId());
            throw new NotFoundException("Province not found");
        }
        Village village = Village.builder()
                .id(villageDTO.getId())
                .name(villageDTO.getName())
                .coords(villageDTO.getCoords())
                .province(province)
                .festivity(festivity)
                .imageUrl(villageDTO.getImageUrl())
                .build();
        villageUseCasesPort.createVillage(village);
        return ResponseEntity.status(HttpStatus.CREATED).body(villageDTO);
    }
}
