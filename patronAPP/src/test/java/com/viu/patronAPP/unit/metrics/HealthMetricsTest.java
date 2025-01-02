package com.viu.patronAPP.unit.metrics;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoDatabase;
import com.viu.patronAPP.metrics.ServiceHealthIndicator;
import io.micrometer.core.instrument.MeterRegistry;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ServiceHealthIndicatorTest {

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ServiceHealthIndicator serviceHealthIndicator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHealthReturnsUp() {
        Health health = serviceHealthIndicator.health();
        assertNotNull(health);
        assertEquals(Health.up().build(), health);
    }

    @Test
    void testGetServiceHealthStatus() {
        double status = serviceHealthIndicator.getServiceHealthStatus();
        assertEquals(1.0, status);
    }

    @Test
    void testGetMongoHealthStatusWhenMongoIsHealthy() {
        MongoDatabase mockDb = mock(MongoDatabase.class);
        when(mongoTemplate.getDb()).thenReturn(mockDb);
        when(mockDb.runCommand(new Document("ping", 1))).thenReturn(new Document("ok", 1.0));

        double status = serviceHealthIndicator.getMongoHealthStatus();
        assertEquals(1.0, status);
    }


    @Test
    void testGetMongoHealthStatusWhenMongoIsDown() {
        MongoDatabase mockDb = mock(MongoDatabase.class);
        when(mongoTemplate.getDb()).thenReturn(mockDb);
        doThrow(new MongoClientException("Mongo is down")).when(mockDb).runCommand(new Document("ping", 1));

        double status = serviceHealthIndicator.getMongoHealthStatus();
        assertEquals(0.0, status);
    }


    @Test
    void testGetStatusCode() {
        assertEquals(1, serviceHealthIndicator.getStatusCode(Health.up().build()));
        assertEquals(2, serviceHealthIndicator.getStatusCode(Health.outOfService().build()));
        assertEquals(0, serviceHealthIndicator.getStatusCode(Health.down().build()));
    }
}
