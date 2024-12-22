package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village;

import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageMongoRepository extends MongoRepository<VillageEntity, String> {
    List<VillageEntity> findByProvinceIdOrderById(String provinceId, Pageable pageable);

    List<VillageEntity> findByProvinceId(String provinceId);

    Optional<VillageEntity> findByFestivity(String festivityId);
}
