package com.viu.patronAPP.metrics;

import com.mongodb.client.MongoDatabase;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MongoMetrics {

    private final MeterRegistry meterRegistry;
    private final MongoTemplate mongoTemplate;

    private static final String DB_SIZE_METRIC = "mongo_db_size_bytes";

    @PostConstruct
    public void init() {
        registerMetrics();
    }

    public void registerMetrics() {
        Gauge.builder(DB_SIZE_METRIC, this, mongoMetrics -> getMongoDatabaseSize())
                .description("Tamaño de la base de datos MongoDB en bytes")
                .register(meterRegistry);
    }

    public double getMongoDatabaseSize() {
        double size = 0.0;
        try {
            MongoDatabase database = mongoTemplate.getDb();
            Document stats = database.runCommand(new Document("dbStats", 1));
            size = stats.getDouble("dataSize");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
