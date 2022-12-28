#!/bin/bash
# Author: Rohtash Lakra
clear
echo
DOCKER_NETWORK_NAME=docker-network
DOCKER_IMAGE_PREFIX=${1:-dockerlakra}
SERVICE_NAME=${2:-docker-microservice}
SERVICE_PORT=${3:-8080}
DOCKER_NAME="${DOCKER_IMAGE_PREFIX}/${SERVICE_NAME}"
#docker run --name=$MYSQL_CONTAINER_NAME \
#	-p$MYSQL_PORT:$MYSQL_PORT \
#	-v $MYSQL_VOLUME_NAME:/var/lib/mysql \
#	-e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
#	--network $DOCKER_NETWORK_NAME \
#	--restart unless-stopped \
#	-d mysql/mysql-server:$MYSQL_VERSION
docker container run --network $DOCKER_NETWORK_NAME --name $SERVICE_NAME -p 8080:8080 -d $DOCKER_NAME
echo

