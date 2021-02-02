#!/usr/bin/env bash
echo "Shutting down";
docker-compose -f docker/docker-compose-database.yml down;
docker-compose -f docker/docker-compose-logs.yml down;
docker-compose -f docker/docker-compose-monitoring.yml down
docker-compose -f docker/docker-compose-redis.yml down
docker-compose -f docker/docker-compose-localhost.yml down