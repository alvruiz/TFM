package com.viu.patronAPP.metrics;

import com.mongodb.MongoClientException;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ServiceHealthIndicator implements HealthIndicator {

    private final MeterRegistry meterRegistry;
    private final MongoTemplate mongoTemplate;

    private static final String SERVICE_HEALTH_STATUS = "service_health_status";
    private static final String MONGO_HEALTH_STATUS = "mongo_health_status";

    @Override
    public Health health() {
        Gauge.builder(SERVICE_HEALTH_STATUS, this, ServiceHealthIndicator::getServiceHealthStatus)
                .register(meterRegistry);

        Gauge.builder(MONGO_HEALTH_STATUS, this, ServiceHealthIndicator::getMongoHealthStatus)
                .register(meterRegistry);

        return Health.up().build();
    }

    public double getServiceHealthStatus() {
        return 1.0;
    }

    public double getMongoHealthStatus() {
        try {
            mongoTemplate.getDb().runCommand(new org.bson.Document("ping", 1));
            return 1.0;
        } catch (MongoClientException e) {
            return 0.0;
        }
    }

    public int getStatusCode(Health health) {
        Status status = health.getStatus();
        if (Status.UP.equals(status)) {
            return 1;
        }
        if (Status.OUT_OF_SERVICE.equals(status)) {
            return 2;
        }
        if (Status.DOWN.equals(status)) {
            return 0;
        }
        return 0;
    }
}
