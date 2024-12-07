package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Festivity;

public interface FestivityUseCasesPort {

    public Festivity createFestivity(Festivity festivity);

    public Festivity getFestivityByVillageId(String villageId);
}
