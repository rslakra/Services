#!/bin/bash
# Author: Rohtash Lakra
clear
echo
DOCKER_IMAGE_PREFIX=${1:-dockerlakra}
DOCKER_IMAGE_NAME=${2:-docker-microservice}
NAME="${DOCKER_IMAGE_PREFIX}/${DOCKER_IMAGE_NAME}"
docker image build -t $NAME .
#docker build -t $NAME .
echo

