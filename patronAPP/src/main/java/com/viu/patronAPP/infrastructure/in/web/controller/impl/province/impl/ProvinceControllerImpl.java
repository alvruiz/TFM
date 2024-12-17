package com.viu.patronAPP.infrastructure.in.web.controller.impl.province.impl;

import com.viu.patronAPP.application.services.ProvinceServiceImpl;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import com.viu.patronAPP.infrastructure.DTO.province.ProvinceDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.province.ProvinceController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class ProvinceControllerImpl implements ProvinceController {
    private final ProvinceServiceImpl provinceService;


    public ResponseEntity<List<ProvinceDTO>> getProvinces() {
        log.info("Get provinces");
        return ResponseEntity.ok(provinceService.getProvinces().stream().map(province -> ProvinceDTO.builder()
                .id(province.getId())
                .name(province.getName())
                .coords(province.getCoords())
                .image(province.getImage())
                .autonomousCommunity(province.getAutonomousCommunity())
                .build()).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<VillageDTO>> getVillagesbyProvince(String provinceId, String page, String size) {
        log.info("Get villages by province");
        List<Village> villages = provinceService.getVillagesByProvince(provinceId, page, size);
        return getListResponseEntity(villages);
    }

    @Override
    public ResponseEntity<List<VillageDTO>> getAllVillages(String provinceId) {
        log.info("Get villages by province");
        List<Village> villages = provinceService.getAllVillages(provinceId);
        return getListResponseEntity(villages);
    }

    private ResponseEntity<List<VillageDTO>> getListResponseEntity(List<Village> villages) {
        log.info("Get list response entity");
        return ResponseEntity.ok(villages.stream().map(village -> VillageDTO.builder()
                .name(village.getName())
                .id(village.getId())
                .coords(village.getCoords())
                .imageUrl(village.getImageUrl())
                .provinceId(village.getProvinceId())
                .festivity(village.getFestivity() != null ? FestivityDTO.builder()
                        .id(village.getFestivity().getId())
                        .name(village.getFestivity().getName())
                        .startDate(village.getFestivity().getStartDate())
                        .endDate(village.getFestivity().getEndDate())
                        .patron(village.getFestivity().getPatron())
                        .villageId(village.getFestivity().getVillageId())
                        .build() : null)
                .build()).collect(Collectors.toList()));
    }


}
