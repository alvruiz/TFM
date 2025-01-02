package com.viu.patronAPP.unit.repository;

import com.viu.patronAPP.infrastructure.out.persistence.repository.ProvinceRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.province.ProvinceMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProvinceRepositoryAdapterTest {

    @Mock
    private ProvinceMongoRepository provinceMongoRepository;
    @InjectMocks
    private ProvinceRepositoryAdapter provinceRepositoryAdapter;

    @Test
    public void getProvinces() {
        provinceRepositoryAdapter.getProvinces();
    }

    @Test
    public void getProvinceById() {
        provinceRepositoryAdapter.getProvinceById("1");
    }
}
