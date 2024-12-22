package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class VillageServiceImpl implements VillageUseCasesPort {
    private final VillagePort villagePort;
    private final FestivityPort festivityPort;

    @Override
    public Village getVillageById(String id) {
        Village village = villagePort.getVillageById(id);
        if (village == null) {
            log.info("Village not found: {}", id);
            throw new NotFoundException("Village not found");
        }
        return village;
    }

    @Override
    public Village getVillageByFestivity(Festivity festivity) {
        Village village = villagePort.getVillageByFestivity(festivity.getId());
        if (village == null) {
            log.info("Village not found for festivity id: {}", festivity.getId());
            throw new NotFoundException("Village not found");
        }
        return village;
    }

}
