package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Village createVillage(Village village) {
        Village villageExists = villagePort.getVillageById(village.getId());
        if (villageExists != null) {
            log.info("Village already exists: {}", village.getId());
            throw new GeneralException("Village already exists");
        }
        return villagePort.createVillage(village);
    }

    @Override
    public void updateVillage(String villageId, Village village) {
        Village villageToUpdate = villagePort.getVillageById(villageId);
        if (villageToUpdate == null) {
            log.info("Village not found for id: {}", villageId);
            throw new NotFoundException("Village not found");
        }
        villageToUpdate.setName(village.getName());
        villageToUpdate.setCoords(village.getCoords());
        villageToUpdate.setImageUrl(village.getImageUrl());
        villagePort.updateVillage(villageId, villageToUpdate);
    }

    @Override
    public void deleteVillage(String villageId) {
        Village village = villagePort.getVillageById(villageId);
        if (village == null) {
            log.info("Village not found for id: {}", villageId);
            throw new NotFoundException("Village not found");
        }
        villagePort.deleteVillage(villageId);
    }

    @Override
    public List<Village> getAllVillages() {
        return villagePort.getAllVillages();
    }
}
