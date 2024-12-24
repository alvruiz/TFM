package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Village;

import java.util.List;

public interface VillagePort {
    public List<Village> getVillagesByProvince(String provinceId, String page, String size);

    public List<Village> getAllVillages();

    public List<Village> getAllVillagesByProvinceID(String provinceId);

    public Village getVillageById(String id);

    public Village getVillageByFestivity(String festivityId);

    public Village createVillage(Village village);

    public void updateVillage(String villageId, Village village);

    public void deleteVillage(String villageId);
}
