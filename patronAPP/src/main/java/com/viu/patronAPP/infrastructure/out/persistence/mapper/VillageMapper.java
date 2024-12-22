package com.viu.patronAPP.infrastructure.out.persistence.mapper;

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
                .province(ProvinceMapper.mapProvinceDomainToEntity(village.getProvince()))
                .festivity(FestivityMapper.mapFestivityDomainToEntity(village.getFestivity()))
                .build();
    }

    public static Village mapVillageEntityToDomain(VillageEntity villageEntity) {
        return Village.builder()
                .id(villageEntity.getId())
                .name(villageEntity.getName())
                .coords(villageEntity.getCoords())
                .imageUrl(villageEntity.getImageUrl())
                .province(ProvinceMapper.mapProvinceEntityToDomain(villageEntity.getProvince()))
                .festivity(FestivityMapper.mapFestivityEntityToDomain(villageEntity.getFestivity()))
                .build();
    }
}
