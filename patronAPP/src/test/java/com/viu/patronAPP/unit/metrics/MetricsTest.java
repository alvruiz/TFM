package com.viu.patronAPP.unit.metrics;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.infrastructure.out.persistence.repository.EventRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.VillageRepositoryAdapter;
import com.viu.patronAPP.metrics.Metrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MetricsTest {

    @Mock
    private VillageRepositoryAdapter villageRepositoryAdapter;

    @Mock
    private UserRepositoryAdapter userRepositoryAdapter;

    @Mock
    private EventRepositoryAdapter eventRepositoryAdapter;

    @Mock
    private MeterRegistry meterRegistry;

    @InjectMocks
    private Metrics metrics;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        metrics = new Metrics(meterRegistry, villageRepositoryAdapter, eventRepositoryAdapter, userRepositoryAdapter);
    }

    @Test
    void testCountVillages() {
        when(villageRepositoryAdapter.countVillages()).thenReturn(10);
        int result = metrics.countVillages();
        assertEquals(10, result);
    }

    @Test
    void testCountUsersRegistered() {
        when(userRepositoryAdapter.getNormalUsers()).thenReturn(Arrays.asList(User.builder().id("1").build(), User.builder().id("2").build(), User.builder().id("3").build()));
        int result = metrics.countUsersRegistered();
        assertEquals(3, result);
    }

    @Test
    void testCountAdmins() {
        when(userRepositoryAdapter.getAdmins()).thenReturn(Arrays.asList(User.builder().id("1").build(), User.builder().id("2").build()));
        int result = metrics.countAdmins();
        assertEquals(2, result);
    }

    @Test
    void testCountCMs() {
        when(userRepositoryAdapter.getCMs()).thenReturn(Arrays.asList(User.builder().id("2").rol(Rol.CM).build()));
        int result = metrics.countCMs();
        assertEquals(1, result);
    }

    @Test
    void testCountEvents() {
        when(eventRepositoryAdapter.countEvents()).thenReturn(5);
        int result = metrics.countEvents();
        assertEquals(5, result);
    }
}
