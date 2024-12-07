package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event;

import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventMongoRepository extends MongoRepository<EventEntity, String> {
}
