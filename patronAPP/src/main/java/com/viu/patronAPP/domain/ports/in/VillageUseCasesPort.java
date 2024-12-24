package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;

import java.util.List;

public interface VillageUseCasesPort {
    public Village getVillageById(String id);

    public Village getVillageByFestivity(Festivity festivity);

    public Village createVillage(Village village);

    public void updateVillage(String villageId, Village village);

    public void deleteVillage(String villageId);

    public List<Village> getAllVillages();


}
