package com.viu.patronAPP.infrastructure.out.persistence.mapper.user;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.ProvinceEntity;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {

    public static ProvinceEntity mapProvinceDomainToEntity(Province province) {
        return ProvinceEntity.builder()
                .id(province.getId())
                .name(province.getName())
                .coords(province.getCoords())
                .imageUrl(province.getImage())
                .autonomousCommunity(province.getAutonomousCommunity())
                .build();
    }

    public static Province mapProvinceEntityToDomain(ProvinceEntity provinceEntity) {
        return Province.builder()
                .id(provinceEntity.getId())
                .name(provinceEntity.getName())
                .coords(provinceEntity.getCoords())
                .image(provinceEntity.getImageUrl())
                .autonomousCommunity(provinceEntity.getAutonomousCommunity())
                .build();
    }
}
