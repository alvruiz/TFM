package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.ports.out.ProvincePort;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.ProvinceMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.province.ProvinceMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProvinceRepositoryAdapter implements ProvincePort {
    private final ProvinceMongoRepository provinceRepository;


    public List<Province> getProvinces() {
        return provinceRepository.findAll().stream().map(ProvinceMapper::mapProvinceEntityToDomain).toList();
    }

    public Province getProvinceById(String provinceId) {
        return provinceRepository.findById(provinceId).map(ProvinceMapper::mapProvinceEntityToDomain).orElse(null);
    }

}
