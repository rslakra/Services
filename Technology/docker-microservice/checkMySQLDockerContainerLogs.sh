#!/bin/bash
# Author: Rohtash Lakra
clear
echo
MYSQL_CONTAINER_NAME=${1:-mysql-docker}
docker container logs -f $MYSQL_CONTAINER_NAME
echo

