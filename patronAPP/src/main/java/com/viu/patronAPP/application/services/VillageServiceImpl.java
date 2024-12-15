package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VillageServiceImpl implements VillageUseCasesPort {
    private final VillagePort villagePort;
    private final FestivityPort festivityPort;

    @Override
    public Village getVillageById(String id) {
        Village village = villagePort.getVillageById(id);
        if (village == null) {
            throw new NotFoundException("Village not found");
        }
        village.setFestivity(festivityPort.getFestivityByVillageId(village.getId()));
        return village;
    }
}
