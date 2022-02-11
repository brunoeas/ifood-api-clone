package com.brunosoares.ifood.cadastro;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class CadastroIfoodTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        final Map<String, String> props = new HashMap<>();

        // Banco de dados
        props.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        props.put("quarkus.datasource.username", POSTGRES.getUsername());
        props.put("quarkus.datasource.password", POSTGRES.getPassword());

        return props;
    }

    @Override
    public void stop() {
        if (POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }

}