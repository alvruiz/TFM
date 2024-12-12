package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.FestivityMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.festivity.FestivityMongoRepository;
import lombok.AllArgsConstructor;
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
    public Festivity getFestivityByVillageId(String villageId) {
        return FestivityMapper.mapFestivityEntityToDomain(Objects.requireNonNull(festivityRepository.findByVillageId(villageId).orElse(null)));
    }

    @Override
    public List<Festivity> getAllFestivities() {
        return festivityRepository.findAll().stream().map(FestivityMapper::mapFestivityEntityToDomain).toList();
    }
}
