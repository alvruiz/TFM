package com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.user;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByRol(Rol rol);
}
