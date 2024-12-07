package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.ProvinceUseCasesPort;
import com.viu.patronAPP.domain.ports.out.ProvincePort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinceServiceImpl implements ProvinceUseCasesPort {

    private final ProvincePort provincePort;
    private final VillagePort villagePort;

    @Override
    public List<Province> getProvinces() {
        return provincePort.getProvinces();
    }

    @Override
    public Province getProvinceById(String provinceId) {
        return provincePort.getProvinceById(provinceId);
    }

    @Override
    public List<Village> getVillagesByProvince(String provinceId) {
        Province province = getProvinceById(provinceId);
        if (province == null) {
            throw new NotFoundException("Province not found");
        }
        return villagePort.getVillagesByProvince(provinceId);
    }


}
