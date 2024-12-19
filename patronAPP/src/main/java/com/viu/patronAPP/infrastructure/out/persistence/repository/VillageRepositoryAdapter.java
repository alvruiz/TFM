package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.VillageMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class VillageRepositoryAdapter implements VillagePort {
    private final VillageMongoRepository villageRepository;

    @Override
    public List<Village> getVillagesByProvince(String provinceId, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(Sort.Direction.ASC, "id"));
        return villageRepository.findByProvinceIdOrderById(provinceId, pageable).stream().map(VillageMapper::mapVillageEntityToDomain).toList();
    }

    @Override
    public List<Village> getAllVillages(String provinceId) {
        return villageRepository.findByProvinceId(provinceId).stream().map(VillageMapper::mapVillageEntityToDomain).toList();
    }

    @Override
    public Village getVillageById(String id) {
        return villageRepository.findById(id).map(VillageMapper::mapVillageEntityToDomain).orElse(null);
    }

    public int countVillages() {
        return (int) villageRepository.count();
    }

}
