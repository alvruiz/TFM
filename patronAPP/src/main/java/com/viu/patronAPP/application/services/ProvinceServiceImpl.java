package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.ProvinceUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.ProvincePort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProvinceServiceImpl implements ProvinceUseCasesPort {

    private final ProvincePort provincePort;
    private final VillagePort villagePort;
    private final FestivityPort festivityPort;

    @Override
    public List<Province> getProvinces() {
        return provincePort.getProvinces();
    }

    @Override
    public Province getProvinceById(String provinceId) {
        Province province = provincePort.getProvinceById(provinceId);
        if (province == null) {
            log.info("Province not found: {}", provinceId);
            throw new NotFoundException("Province not found");
        }
        return province;
    }

    @Override
    public List<Village> getVillagesByProvince(String provinceId, String page, String size) {
        Province province = getProvinceById(provinceId);
        if (province == null) {
            log.info("Province not found: {}", provinceId);
            throw new NotFoundException("Province not found");
        }
        return villagePort.getVillagesByProvince(provinceId, page, size);
    }

    @Override
    public List<Village> getAllVillages(String provinceId) {
        return villagePort.getAllVillagesByProvinceID(provinceId);
    }


}
