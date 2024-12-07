package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
