package com.gade;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Factory
@EnableR2dbcRepositories
public class Configuration extends AbstractR2dbcConfiguration {

    @Value("${poc.postgres.host}")
    private String host;
    @Value("${poc.postgres.port}")
    private int port;
    @Value("${poc.postgres.database}")
    private String database;
    @Value("${poc.postgres.username}")
    private String username;
    @Value("${poc.postgres.password}")
    private String password;

    @Bean
    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(host)
                        .port(port)
                        .database(database)
                        .username(username)
                        .password(password)
                        .build());
    }

    @Bean
    public R2dbcRepositoryFactory repositoryFactory(final DatabaseClient client,
                                                    final RelationalMappingContext context,
                                                    final ReactiveDataAccessStrategy strategy) {
        return new R2dbcRepositoryFactory(client, context, strategy);
    }

    @Bean
    public PizzaRepository coffeeRepository(final R2dbcRepositoryFactory factory) {
        return factory.getRepository(PizzaRepository.class);
    }
}
