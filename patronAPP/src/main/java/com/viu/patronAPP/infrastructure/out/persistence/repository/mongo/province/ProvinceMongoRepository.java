package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.province;

import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.ProvinceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceMongoRepository extends MongoRepository<ProvinceEntity, String> {

}
