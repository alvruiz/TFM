package com.viu.patronAPP.unit.repository;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.ProvinceEntity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.VillageRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VillageRepositoryAdapterTest {

    @Mock
    private VillageMongoRepository villageMongoRepository;
    @InjectMocks
    private VillageRepositoryAdapter villageRepositoryAdapter;

    Village village = Village.builder().id("1")
            .festivity(Festivity.builder().events(new ArrayList<>()).build()).province(Province.builder().build()).build();
    VillageEntity villageEntity = VillageEntity.builder().id("1").province(
                    ProvinceEntity.builder().build()
            )
            .festivity(FestivityEntity.builder().events(new ArrayList<>()).build())

            .build();

    @Test
    public void testGetAllVillages() {
        when(villageMongoRepository.findAll()).thenReturn(List.of(villageEntity));
        List<Village> villages = villageRepositoryAdapter.getAllVillages();
        assert villages.size() == 1;
        assert villages.get(0).getId().equals(village.getId());
        verify(villageMongoRepository).findAll();
    }

    @Test
    public void testGetVillagesByProvince() {
        when(villageMongoRepository.findByProvinceIdOrderById("1", PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")))).thenReturn(List.of(villageEntity));
        List<Village> villages = villageRepositoryAdapter.getVillagesByProvince("1", "0", "1");
        assert villages.size() == 1;
        assert villages.get(0).getId().equals(village.getId());
        verify(villageMongoRepository).findByProvinceIdOrderById("1", PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
    }

    @Test
    public void testGetAllVillagesByProvince() {
        when(villageMongoRepository.findByProvinceId("1")).thenReturn(List.of(villageEntity));
        List<Village> villages = villageRepositoryAdapter.getAllVillagesByProvinceID("1");
        assert villages.size() == 1;
        assert villages.get(0).getId().equals(village.getId());
        verify(villageMongoRepository).findByProvinceId("1");
    }

    @Test
    public void testGetVillageById() {
        when(villageMongoRepository.findById("1")).thenReturn(Optional.ofNullable(villageEntity));
        Village village = villageRepositoryAdapter.getVillageById("1");
        assert village.getId().equals(villageEntity.getId());
        verify(villageMongoRepository).findById("1");
    }

    @Test
    public void testGetVillageByFestivity() {
        when(villageMongoRepository.findByFestivity("1")).thenReturn(Optional.ofNullable(villageEntity));
        Village villages = villageRepositoryAdapter.getVillageByFestivity("1");
        assert villages != null;
        assert village.getId().equals(villageEntity.getId());
        verify(villageMongoRepository, times(2)).findByFestivity("1");
    }

    @Test
    public void testCreateVillage() {
        when(villageMongoRepository.save(villageEntity)).thenReturn(villageEntity);
        Village villageResult = villageRepositoryAdapter.createVillage(village);
        assert villageResult != null;
        assert villageResult.equals(village);
        verify(villageMongoRepository).save(villageEntity);
    }

    @Test
    public void testUpdateVillage() {
        when(villageMongoRepository.save(villageEntity)).thenReturn(villageEntity);
        villageRepositoryAdapter.updateVillage("1", village);
        verify(villageMongoRepository).save(villageEntity);

    }

    @Test
    public void testDeleteVillage() {
        villageRepositoryAdapter.deleteVillage("1");
        verify(villageMongoRepository).deleteById("1");
    }

    @Test
    public void testCountVillages() {
        when(villageMongoRepository.count()).thenReturn(1L);
        int result = villageRepositoryAdapter.countVillages();
        assert result == 1;
        verify(villageMongoRepository).count();
    }
}
