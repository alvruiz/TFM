package com.viu.patronAPP.unit.metrics;

import com.mongodb.client.MongoDatabase;
import com.viu.patronAPP.metrics.MongoMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MongoMetricsTest {

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private MongoDatabase mongoDatabase;

    @InjectMocks
    private MongoMetrics mongoMetrics;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoTemplate.getDb()).thenReturn(mongoDatabase);
    }

    @Test
    void testGetMongoDatabaseSize() {
        Document stats = new Document("dataSize", 2048.0);
        when(mongoDatabase.runCommand(new Document("dbStats", 1))).thenReturn(stats);

        double result = mongoMetrics.getMongoDatabaseSize();

        assertEquals(2048.0, result);
    }

    @Test
    void testGetMongoDatabaseSizeHandlesException() {
        when(mongoDatabase.runCommand(new Document("dbStats", 1))).thenThrow(new RuntimeException("Database error"));

        double result = mongoMetrics.getMongoDatabaseSize();

        assertEquals(0.0, result);
    }
}
