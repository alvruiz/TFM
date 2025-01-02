package com.viu.patronAPP.services;

import com.viu.patronAPP.application.services.FestivityServiceImpl;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FestivityServiceTest {

    @Mock
    private FestivityPort festivityPort;
    @Mock
    private VillagePort villagePort;

    @InjectMocks
    private FestivityServiceImpl festivityService;

    Festivity festivity = Festivity.builder().id("1")
            .name("festivity")
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 1, 1))
            .patron("Patron")
            .events(new ArrayList<>())
            .build();
    Village village = Village.builder().id("1")
            .name("village")
            .festivity(festivity)
            .build();
    Village villageWithoutFestivity = Village.builder().id("1")
            .name("village")
            .build();

    @Test
    public void testCreateFestivity() {
        when(festivityPort.getById("1")).thenReturn(null);
        when(festivityPort.createFestivity(festivity)).thenReturn(festivity);
        Festivity festivityResult = festivityService.createFestivity(festivity);

        verify(festivityPort, times(1)).createFestivity(festivity);
        verify(festivityPort, times(1)).getById("1");
        assert (festivityResult.equals(festivity));
    }

    @Test
    public void testCreateExistingFestivity() {
        when(festivityPort.getById("1")).thenReturn(festivity);
        GeneralException generalException = assertThrows(GeneralException.class, () -> festivityService.createFestivity(festivity));

        verify(festivityPort, times(0)).createFestivity(festivity);
        verify(festivityPort, times(1)).getById("1");
        assert (generalException.getMessage().equals("Festivity already exists"));
    }

    @Test
    public void testGetFestivityByEvent() {
        when(festivityPort.getFestivityByEvent("1")).thenReturn(festivity);
        Festivity festivityResult = festivityService.getFestivityByEvent("1");
        verify(festivityPort, times(1)).getFestivityByEvent("1");
        assert (festivityResult.equals(festivity));
    }

    @Test
    public void testGetFestivityByEventNotFound() {
        when(festivityPort.getFestivityByEvent("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> festivityService.getFestivityByEvent("1"));

        verify(festivityPort, times(1)).getFestivityByEvent("1");
        assert (exception.getMessage().equals("Festivity not found"));
    }

    @Test
    public void testGetFestivityByVillage() {
        when(villagePort.getVillageById("1")).thenReturn(village);
        Festivity festivityResult = festivityService.getFestivityByVillageId("1");
        verify(villagePort, times(1)).getVillageById("1");
        assert (festivityResult.equals(festivity));
    }

    @Test
    public void testGetFestivityByVillageNull() {
        when(villagePort.getVillageById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> festivityService.getFestivityByVillageId("1"));

        verify(villagePort, times(1)).getVillageById("1");
        assert (exception.getMessage().equals("Village not found"));
    }

    @Test
    public void testGetFestivityByVillageFestivityNull() {
        when(villagePort.getVillageById("1")).thenReturn(villageWithoutFestivity);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> festivityService.getFestivityByVillageId("1"));

        verify(villagePort, times(1)).getVillageById("1");
        assert (exception.getMessage().equals("Festivity not found"));
    }

    @Test
    public void testGetAllFestivities() {
        when(festivityPort.getAllFestivities("0", "0")).thenReturn(Collections.singletonList(festivity));
        List<Festivity> festivities = festivityService.getAllFestivities("0", "0");

        verify(festivityPort, times(1)).getAllFestivities("0", "0");
        assertEquals(1, festivities.size());
    }

    @Test
    public void testGetFestivityById() {
        when(festivityPort.getById("1")).thenReturn(festivity);
        Festivity festivity = festivityService.getById("1");

        verify(festivityPort, times(1)).getById("1");
        assertEquals(festivity.getId(), "1");
    }

    @Test
    public void testGetFestivityByIdFestivityNull() {
        when(festivityPort.getById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> festivityService.getById("1"));

        verify(festivityPort, times(1)).getById("1");
        assert (exception.getMessage().equals("Festivity not found"));
    }

    @Test
    public void testUpdateFestivity() {
        when(festivityPort.getById("1")).thenReturn(festivity);
        when(festivityPort.updateFestivity(festivity)).thenReturn(festivity);
        Festivity newFestivity = Festivity.builder()
                .id("1")
                .name("New Festivity")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .patron("New patron")
                .events(new ArrayList<>())
                .build();
        festivityService.updateFestivity("1", newFestivity);
        verify(festivityPort, times(1)).updateFestivity(newFestivity);
        verify(festivityPort, times(1)).getById("1");
        assertEquals(newFestivity.getId(), "1");
        assertEquals(newFestivity.getName(), "New Festivity");
        assertEquals(newFestivity.getPatron(), "New patron");
    }

    @Test
    public void testUpdateFestivityFestivityNull() {
        when(festivityPort.getById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> festivityService.updateFestivity("1", festivity));

        verify(festivityPort, times(1)).getById("1");
        assert (exception.getMessage().equals("Festivity not found"));
    }
}
