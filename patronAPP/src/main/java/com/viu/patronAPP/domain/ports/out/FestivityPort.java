package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Festivity;

import java.util.List;

public interface FestivityPort {
    public Festivity createFestivity(Festivity festivity);


    public List<Festivity> getAllFestivities(String page, String size);

    public Festivity getById(String festivityId);

    public Festivity getFestivityByEvent(String eventId);
}
