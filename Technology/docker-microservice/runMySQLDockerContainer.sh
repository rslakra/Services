#!/bin/bash
# Author: Rohtash Lakra
clear
echo
DOCKER_NETWORK_NAME=docker-network
MYSQL_CONTAINER_NAME=${1:-mysql-docker}
MYSQL_PORT=${2:-3306}
MYSQL_ROOT_PASSWORD=${3:-root}
MYSQL_DATABASE=${4:-employeedb}
MYSQL_VERSION=${5:-latest}
#docker run --name=$MYSQL_CONTAINER_NAME \
#	-p$MYSQL_PORT:$MYSQL_PORT \
#	-v $MYSQL_VOLUME_NAME:/var/lib/mysql \
#	-e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
#	--network $DOCKER_NETWORK_NAME \
#	--restart unless-stopped \
#	-d mysql/mysql-server:$MYSQL_VERSION
docker container run --name $MYSQL_CONTAINER_NAME --network $DOCKER_NETWORK_NAME -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=$MYSQL_DATABASE -d mysql:latest
echo

