package com.brunosoares.ifood.mp.config;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class FlywayInitialization {

    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    String datasourceUrl;

    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;

    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    public void runFlywayMigration(@Observes StartupEvent event) {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:" + datasourceUrl, datasourceUsername, datasourcePassword)
                .load();
        flyway.migrate();
    }

}
