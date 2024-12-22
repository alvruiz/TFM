package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Festivity;

import java.util.List;

public interface FestivityUseCasesPort {

    public Festivity createFestivity(Festivity festivity);

    public Festivity getFestivityByVillageId(String villageId);

    public List<Festivity> getAllFestivities(String page, String size);

    public Festivity getById(String festivityId);

    public Festivity getFestivityByEvent(String eventId);
}
