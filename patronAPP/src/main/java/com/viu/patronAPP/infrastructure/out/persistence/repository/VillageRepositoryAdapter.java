package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.VillageMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class VillageRepositoryAdapter implements VillagePort {
    private final VillageMongoRepository villageRepository;

    public List<Village> getVillagesByProvince(String provinceId, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        return villageRepository.findByProvinceId(provinceId, pageable).stream().map(VillageMapper::mapVillageEntityToDomain).toList();
    }
}
