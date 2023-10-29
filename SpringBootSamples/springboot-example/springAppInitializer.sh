#!/bin/bash
#Author: Rohtash Lakra
clear
echo
curl https://start.spring.io/starter.tgz \
  -d bootVersion=3.0.5 \
  -d dependencies=web \
  -d type=maven-project \
  -d baseDir=springboot-example | tar -xzvf -
echo
