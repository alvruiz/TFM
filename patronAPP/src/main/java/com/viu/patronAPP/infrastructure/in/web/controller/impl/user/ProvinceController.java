package com.viu.patronAPP.infrastructure.in.web.controller.impl.user;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/province")
public interface ProvinceController {

    @GetMapping("/")
    public ResponseEntity<List<Province>> getProvinces();

    @GetMapping("/{provinceId}")
    public ResponseEntity<List<Village>> getVillagesbyProvince(@PathVariable String provinceId);
}
