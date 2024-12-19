package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event;

import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMongoRepository extends MongoRepository<EventEntity, String> {
    List<EventEntity> findByFestivityId(String festivityId);

    List<EventEntity> findByAttendeesContaining(String userId);

    List<EventEntity> findByIdIn(List<String> ids);

}
