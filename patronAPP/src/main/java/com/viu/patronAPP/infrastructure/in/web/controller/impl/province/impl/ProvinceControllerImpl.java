package com.viu.patronAPP.infrastructure.in.web.controller.impl.province.impl;

import com.viu.patronAPP.application.services.ProvinceServiceImpl;
import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.province.ProvinceController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProvinceControllerImpl implements ProvinceController {
    private final ProvinceServiceImpl provinceService;


    public ResponseEntity<List<Province>> getProvinces() {
        return ResponseEntity.ok(provinceService.getProvinces());
    }

    public ResponseEntity<List<Village>> getVillagesbyProvince(String provinceId, String page, String size) {
        return ResponseEntity.ok(provinceService.getVillagesByProvince(provinceId, page, size));
    }

}
