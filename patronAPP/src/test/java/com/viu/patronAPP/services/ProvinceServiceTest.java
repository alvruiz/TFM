package com.viu.patronAPP.services;

import com.viu.patronAPP.application.services.ProvinceServiceImpl;
import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.ProvincePort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProvinceServiceTest {

    @Mock
    private ProvincePort provincePort;
    @Mock
    private VillagePort villagePort;
    @Mock
    private FestivityPort festivityPort;

    @InjectMocks
    private ProvinceServiceImpl provinceService;

    Province province = Province.builder().id("1").build();

    @Test
    public void testGetProvinces() {
        when(provincePort.getProvinces()).thenReturn(List.of(province));
        List<Province> provinces = provinceService.getProvinces();
        assert (provinces.size() == 1);
        assert (provinces.getFirst().getId().equals("1"));
    }

    @Test
    public void testGetVillagesByProvince() {
        when(provincePort.getProvinceById("1")).thenReturn(province);
        when(villagePort.getVillagesByProvince("1", "0", "1")).thenReturn(List.of(Village.builder().id("1").build()));
        List<Village> province = provinceService.getVillagesByProvince("1", "0", "1");
        assert (province.size() == 1);
        assert (province.getFirst().getId().equals("1"));
    }

    @Test
    public void testGetVillagesByProvinceNull() {
        when(provincePort.getProvinceById("1")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            provinceService.getVillagesByProvince("1", "0", "1");
        });
        assert (exception.getMessage().equals("Province not found"));
    }

    @Test
    public void testGetAllVillages() {
        when(villagePort.getAllVillagesByProvinceID("1")).thenReturn(List.of(Village.builder().id("1").build()));

        List<Village> villages = provinceService.getAllVillages("1");
        assert (villages.size() == 1);
        assert (villages.getFirst().getId().equals("1"));
    }

    @Test
    public void testGetProvinceById() {
        when(provincePort.getProvinceById("1")).thenReturn(province);

        Province province = provinceService.getProvinceById("1");
        assertNotNull(province);
    }

    @Test
    public void testGetProvinceByIdNull() {
        when(provincePort.getProvinceById("1")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            provinceService.getProvinceById("1");
        });
        assert (exception.getMessage().equals("Province not found"));
    }
}
