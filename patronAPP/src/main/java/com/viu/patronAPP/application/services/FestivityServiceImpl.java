package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FestivityServiceImpl implements FestivityUseCasesPort {

    private final FestivityPort festivityPort;

    public Festivity createFestivity(Festivity festivity) {
        Festivity festivityExist = getById(festivity.getId());
        if (festivityExist != null) {
            log.info("Festivity already exists: {}", festivity.getId());
            throw new GeneralException("Festivity already exists");
        }
        return festivityPort.createFestivity(festivity);
    }

    @Override
    public Festivity getFestivityByVillageId(String villageId) {
        Festivity festivity = festivityPort.getFestivityByVillageId(villageId);
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
}
