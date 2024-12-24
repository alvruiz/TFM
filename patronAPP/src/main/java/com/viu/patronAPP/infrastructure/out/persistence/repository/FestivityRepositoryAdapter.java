package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.FestivityMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.festivity.FestivityMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class FestivityRepositoryAdapter implements FestivityPort {

    private final FestivityMongoRepository festivityRepository;

    @Override
    public Festivity createFestivity(Festivity festivity) {
        FestivityEntity entity = FestivityMapper.mapFestivityDomainToEntity(festivity);
        return FestivityMapper.mapFestivityEntityToDomain(festivityRepository.save(entity));
    }


    @Override
    public Festivity getFestivityByEvent(String eventId) {
        return FestivityMapper.mapFestivityEntityToDomain(Objects.requireNonNull(festivityRepository.findByEvents_Id(eventId).orElse(null)));
    }

    @Override
    public List<Festivity> getAllFestivities(String page, String size) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        return festivityRepository.findAll(pageable).stream().map(FestivityMapper::mapFestivityEntityToDomain).toList();
    }


    @Override
    public Festivity getById(String festivityId) {
        FestivityEntity entity = festivityRepository.findById(festivityId).orElse(null);
        return entity != null ? FestivityMapper.mapFestivityEntityToDomain(entity) : null;
    }

    @Override
    public Festivity updateFestivity(Festivity festivity) {
        FestivityEntity entityEntity = FestivityMapper.mapFestivityDomainToEntity(festivity);
        festivityRepository.save(entityEntity);
        return FestivityMapper.mapFestivityEntityToDomain(entityEntity);
    }

}
