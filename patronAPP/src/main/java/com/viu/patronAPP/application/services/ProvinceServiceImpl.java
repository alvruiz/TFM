package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.ProvinceUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
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
    private final FestivityPort festivityPort;

    @Override
    public List<Province> getProvinces() {
        return provincePort.getProvinces();
    }

    @Override
    public Province getProvinceById(String provinceId) {
        return provincePort.getProvinceById(provinceId);
    }

    @Override
    public List<Village> getVillagesByProvince(String provinceId, String page, String size) {
        Province province = getProvinceById(provinceId);
        if (province == null) {
            throw new NotFoundException("Province not found");
        }
        List<Village> villages = villagePort.getVillagesByProvince(provinceId, page, size);
        List<Festivity> festivities = festivityPort.getAllFestivitiesByIds(villages.stream().map(Village::getId).toList());
        villages.forEach(village -> village.setFestivity(festivities.stream().filter(festivity -> festivity.getVillageId().equals(village.getId())).findFirst().orElse(null)));
        return villages;
    }

    @Override
    public List<Village> getAllVillages(String provinceId) {
        List<Village> villages = villagePort.getAllVillages(provinceId);
        List<Festivity> festivities = festivityPort.getAllFestivitiesByIds(villages.stream().map(Village::getId).toList());
        villages.forEach(village -> village.setFestivity(festivities.stream().filter(festivity -> festivity.getVillageId().equals(village.getId())).findFirst().orElse(null)));
        return villages;
    }


}
