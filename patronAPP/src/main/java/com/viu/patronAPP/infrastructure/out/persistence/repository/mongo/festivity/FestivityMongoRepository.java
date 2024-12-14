package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.festivity;

import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FestivityMongoRepository extends MongoRepository<FestivityEntity, String> {
    Optional<FestivityEntity> findByVillageId(String villageId);


}
