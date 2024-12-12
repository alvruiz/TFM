package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Festivity;

import java.util.List;

public interface FestivityPort {
    public Festivity createFestivity(Festivity festivity);

    public Festivity getFestivityByVillageId(String villageId);

    public List<Festivity> getAllFestivities();
}
