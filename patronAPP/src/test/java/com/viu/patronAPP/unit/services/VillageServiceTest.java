package com.viu.patronAPP.unit.services;

import com.viu.patronAPP.application.services.VillageServiceImpl;
import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VillageServiceTest {
    @Mock
    private VillagePort villagePort;
    @Mock
    private FestivityPort festivityPort;
    @InjectMocks
    private VillageServiceImpl villageService;

    Village village = Village.builder().id("1").build();
    Festivity festivity = Festivity.builder().id("1").build();

    @Test
    public void testGetVillageById() {
        when(villagePort.getVillageById("1")).thenReturn(village);
        Village result = villageService.getVillageById("1");
        assert (result != null);
        assert (result.equals(village));
    }

    @Test
    public void testGetVillageByIdNull() {
        when(villagePort.getVillageById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            villageService.getVillageById("1");
        });
        verify(villagePort).getVillageById("1");
        assert (exception != null);
        assert (exception.getMessage().equals("Village not found"));
    }

    @Test
    public void testGetVillageByFestivity() {
        when(villagePort.getVillageByFestivity("1")).thenReturn(village);
        Village result = villageService.getVillageByFestivity(festivity);
        assert (result != null);
        assert (result.equals(village));
    }

    @Test
    public void testGetVillageByFestivityNull() {
        when(villagePort.getVillageByFestivity("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            villageService.getVillageByFestivity(festivity);
        });
        verify(villagePort).getVillageByFestivity("1");
        assert (exception != null);
        assert (exception.getMessage().equals("Village not found"));
    }

    @Test
    public void testCreateVillageNull() {
        when(villagePort.getVillageById("1")).thenReturn(village);
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            villageService.createVillage(village);
        });
        verify(villagePort).getVillageById("1");
        assert (exception != null);
        assert (exception.getMessage().equals("Village already exists"));
    }

    @Test
    public void testCreateVillage() {
        when(villagePort.getVillageById("1")).thenReturn(null);
        when(villagePort.createVillage(village)).thenReturn(village);
        Village result = villageService.createVillage(village);
        assert (result != null);
        assert (result.equals(village));
    }

    @Test
    public void testGetAllVillages() {
        when(villagePort.getAllVillages()).thenReturn(List.of(village));
        List<Village> result = villageService.getAllVillages();
        assert (result != null);
        assert (result.size() == 1);
        assert (result.getFirst().equals(village));
    }

    @Test
    public void testDeleteVillage() {
        when(villagePort.getVillageById("1")).thenReturn(village);
        villageService.deleteVillage("1");
        verify(villagePort).deleteVillage("1");
    }

    @Test
    public void testDeleteVillageNotFound() {
        when(villagePort.getVillageById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            villageService.deleteVillage("1");
        });
        verify(villagePort).getVillageById("1");
        assert (exception != null);
        assert (exception.getMessage().equals("Village not found"));
    }

    @Test
    public void testUpdateVillage() {
        when(villagePort.getVillageById("1")).thenReturn(village);
        Village newVillage = Village.builder().id("1").name("new").build();
        villageService.updateVillage("1", newVillage);
        verify(villagePort).updateVillage("1", newVillage);
        assert (villageService.getVillageById("1").equals(newVillage));

    }

    @Test
    public void testUpdateVillageNotFound() {
        when(villagePort.getVillageById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            villageService.updateVillage("1", village);
        });
        verify(villagePort).getVillageById("1");
        assert (exception != null);
        assert (exception.getMessage().equals("Village not found"));
    }


}
