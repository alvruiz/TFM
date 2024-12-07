package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Festivity;

public interface FestivityPort {
    public Festivity createFestivity(Festivity festivity);

    public Festivity getFestivityByVillageId(String villageId);
}
