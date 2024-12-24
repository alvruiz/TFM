package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FestivityServiceImpl implements FestivityUseCasesPort {

    private final FestivityPort festivityPort;
    private final VillagePort villagePort;

    public Festivity createFestivity(Festivity festivity) {
        boolean exitsFestivity = exitsFestivity(festivity.getId());

        if (exitsFestivity) {
            log.info("Festivity already exists: {}", festivity.getId());
            throw new GeneralException("Festivity already exists");
        }
        return festivityPort.createFestivity(festivity);
    }

    @Override
    public Festivity getFestivityByEvent(String eventId) {
        Festivity festivity = festivityPort.getFestivityByEvent(eventId);
        if (festivity == null) {
            log.info("Festivity not found for event id: {}", eventId);
            throw new NotFoundException("Festivity not found");
        }
        return festivity;

    }

    @Override
    public Festivity getFestivityByVillageId(String villageId) {
        Village village = villagePort.getVillageById(villageId);
        if (village == null) {
            log.info("Village not found for village id: {}", villageId);
            throw new NotFoundException("Village not found");
        }
        Festivity festivity = village.getFestivity();
        if (festivity == null) {
            log.info("Festivity not found for village id: {}", villageId);
            throw new NotFoundException("Festivity not found");
        }
        return festivity;

    }

    @Override
    public List<Festivity> getAllFestivities(String page, String size) {

        return festivityPort.getAllFestivities(page, size);
    }

    @Override
    public Festivity getById(String festivityId) {
        Festivity festivity = festivityPort.getById(festivityId);
        if (festivity == null) {
            log.info("Festivity not found: {}", festivityId);
            throw new NotFoundException("Festivity not found");
        }
        return festivity;
    }

    @Override
    public void updateFestivity(String festivityId, Festivity festivity) {
        Festivity festivityToUpdate = festivityPort.getById(festivityId);
        if (festivityToUpdate == null) {
            log.info("Festivity not found for id: {}", festivityId);
            throw new NotFoundException("Festivity not found");
        }
        festivityToUpdate.setName(festivity.getName());
        festivityToUpdate.setStartDate(festivity.getStartDate());
        festivityToUpdate.setEndDate(festivity.getEndDate());
        festivityPort.updateFestivity(festivityToUpdate);
    }

    public Boolean exitsFestivity(String festivityId) {
        Festivity festivity = festivityPort.getById(festivityId);
        return festivity != null;
    }
}
