#!/bin/bash
# Author: Rohtash Lakra
clear
echo
MYSQL_CONTAINER_NAME=${1:-mysql-docker}
docker container exec -it $MYSQL_CONTAINER_NAME bash
#mysql -uroot -proot
echo

