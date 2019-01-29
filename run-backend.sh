#!/bin/sh

cd micronaut-postgres-reactive

docker-compose -f src/test/resources/docker/docker-compose.yml up -d
./gradlew run
