package com.gade;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Factory
public class Configuration {

    @Bean
    PostgresqlConnectionFactory connectionFactory(final @Value("${poc.postgres.host}") String host,
                                                  final @Value("${poc.postgres.port}") int port,
                                                  final @Value("${poc.postgres.database}") String database,
                                                  final @Value("${poc.postgres.username}") String username,
                                                  final @Value("${poc.postgres.password}") String password) {
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
    DatabaseClient databaseClient(final ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    R2dbcRepositoryFactory repositoryFactory(final DatabaseClient client) {
        final RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client, context);
    }

    @Bean
    PizzaRepository pizzaRepository(final R2dbcRepositoryFactory factory) {
        return factory.getRepository(PizzaRepository.class);
    }
}
