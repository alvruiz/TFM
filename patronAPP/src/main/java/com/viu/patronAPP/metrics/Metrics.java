package com.viu.patronAPP.metrics;

import com.viu.patronAPP.infrastructure.out.persistence.repository.EventRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.VillageRepositoryAdapter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class Metrics {

    private final VillageRepositoryAdapter villageRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final EventRepositoryAdapter eventRepositoryAdapter;


    public Metrics(MeterRegistry meterRegistry, VillageRepositoryAdapter villageRepositoryAdapter, EventRepositoryAdapter eventRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        System.out.println("Metrics constructor");
        this.villageRepositoryAdapter = villageRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.eventRepositoryAdapter = eventRepositoryAdapter;
        meterRegistry.counter("patron_login_attempts");
        Gauge.builder("patronapp_users", this, Metrics::countUsersRegistered)
                .tag("type", "user")
                .description("Número de usuarios registrados en la base de datos")
                .register(meterRegistry);
        Gauge.builder("patronapp_users", this, Metrics::countAdmins)
                .tag("type", "admin")
                .description("Número de administradores en la base de datos")
                .register(meterRegistry);
        Gauge.builder("patronapp_users", this, Metrics::countCMs)
                .tag("type", "cms")
                .description("Número de administradores en la base de datos")
                .register(meterRegistry);
        Gauge.builder("patronapp_villages", this, Metrics::countVillages)
                .description("Número de pueblos en la base de datos")
                .register(meterRegistry);
        Gauge.builder("patronapp_events", this, Metrics::countEvents)
                .description("Número de eventos en la base de datos")
                .register(meterRegistry);
    }

    public int countVillages() {
        return villageRepositoryAdapter.countVillages();
    }

    public int countUsersRegistered() {
        return userRepositoryAdapter.getNormalUsers().size();
    }

    public int countAdmins() {
        return userRepositoryAdapter.getAdmins().size();
    }

    public int countCMs() {
        return userRepositoryAdapter.getCMs().size();
    }

    public int countEvents() {
        return eventRepositoryAdapter.countEvents();
    }


}
