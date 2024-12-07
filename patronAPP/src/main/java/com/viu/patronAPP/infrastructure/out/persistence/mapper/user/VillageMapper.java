package com.viu.patronAPP.infrastructure.out.persistence.mapper.user;

import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import org.springframework.stereotype.Component;

@Component
public class VillageMapper {
    public static VillageEntity mapVillageDomainToEntity(Village village) {
        return VillageEntity.builder()
                .id(village.getId())
                .name(village.getName())
                .coords(village.getCoords())
                .imageUrl(village.getImageUrl())
                .provinceId(village.getProvinceId())
                .build();
    }

    public static Village mapVillageEntityToDomain(VillageEntity villageEntity) {
        return Village.builder()
                .id(villageEntity.getId())
                .name(villageEntity.getName())
                .coords(villageEntity.getCoords())
                .imageUrl(villageEntity.getImageUrl())
                .provinceId(villageEntity.getProvinceId())
                .build();
    }
}
