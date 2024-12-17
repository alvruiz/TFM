package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FestivityServiceImpl implements FestivityUseCasesPort {

    private final FestivityPort festivityPort;

    public Festivity createFestivity(Festivity festivity) {
        return festivityPort.createFestivity(festivity);
    }

    @Override
    public Festivity getFestivityByVillageId(String villageId) {
        return festivityPort.getFestivityByVillageId(villageId);
    }

    @Override
    public List<Festivity> getAllFestivities(String page, String size) {
        return festivityPort.getAllFestivities(page, size);
    }

    @Override
    public Festivity getById(String festivityId) {
        return festivityPort.getById(festivityId);
    }
}
