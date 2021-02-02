#!/usr/bin/env bash

BUILD=$1;

function print_green() {
    echo -e "\e[32m$1\e[0m"
}

function print_error() {
    echo -e "\e[31m[x] $1\e[0m"
}

function print_header() {
  print_green "#############################################################"
  print_green "  ██████╗  █████╗  ██████╗██╗  ██╗███████╗  "
  print_green "  ██╔══██╗██╔══██╗██╔════╝██║ ██╔╝██╔════╝  "
  print_green "  ██║  ██║███████║██║     █████╔╝ █████╗    "
  print_green "  ██║  ██║██╔══██║██║     ██╔═██╗ ██╔══╝    "
  print_green "  ██████╔╝██║  ██║╚██████╗██║  ██╗███████╗  "
  print_green "  ╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝  "
  print_green "#############################################################"
  print_green "              A dev stack!"
  print_green "        Spins up the containers:"
  print_green "        - MySQL database"
  print_green "        - ElasticSearch/MongoDb/Graylogs"
  print_green "        - Prometheus/Grafana"
  print_green "        - Redis"
  print_green "        - Localstack (AWS mocks)"
  print_green "#############################################################"
  echo
}

# ------------------------------------------------------------------
# Script starts here
# ------------------------------------------------------------------
print_header

# ------------------------------------------------------------------
# 1. Startup containers
# ------------------------------------------------------------------

if [[ "$BUILD" = '--build' ]] ; then
    print_green "Starting docker build"
    echo;
    ./build.sh
fi

print_green "[x] Stopping existing containers"
./stop-stack.sh
echo

print_green "[x] MySQL database container"
docker-compose -f docker/docker-compose-database.yml up -d
echo

print_green "[x] Log containers: ElasticSearch/MongoDb/Graylogs"
docker-compose -f docker/docker-compose-logs.yml up -d
echo

print_green "[x] Monitoring containers: Prometheus/Grafana"
docker-compose -f docker/docker-compose-monitoring.yml up -d prometheus grafana
echo

print_green "[x] Redis container: Redis"
docker-compose -f docker/docker-compose-redis.yml up -d
echo

print_green "[x] Localhost (AWS mock)"
docker-compose -f docker/docker-compose-localhost.yml up -d
echo
