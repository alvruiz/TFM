package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;

public interface VillageUseCasesPort {
    public Village getVillageById(String id);

    public Village getVillageByFestivity(Festivity festivity);

}
