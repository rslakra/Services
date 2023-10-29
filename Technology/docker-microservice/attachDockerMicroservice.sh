#!/bin/bash
# Author: Rohtash Lakra
NAME=${$1:docker-microservice}
clear
echo
docker exec -it $NAME
echo

