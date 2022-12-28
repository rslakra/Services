#!/bin/bash
# Author: Rohtash Lakra
clear
echo
SERVICE_NAME=${1:-employee-service}
MYSQL_SERVICE_NAME=${2:-mysqldb}
DOCKER_MSQL_SERVICE_NAME="${SERVICE_NAME}-${MYSQL_SERVICE_NAME}-1"
DOCKER_SERVICE_NAME="${SERVICE_NAME}-employeeService-1"
echo "Docker mysqlServiceName:${DOCKER_MSQL_SERVICE_NAME}, serviceName:${DOCKER_SERVICE_NAME}"
docker stop $DOCKER_MSQL_SERVICE_NAME
docker stop $DOCKER_SERVICE_NAME
docker rm $DOCKER_MSQL_SERVICE_NAME $DOCKER_SERVICE_NAME
docker ps -a
echo

