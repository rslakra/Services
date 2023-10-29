#!/bin/bash
#Author: Rohtash Lakra
clear
echo
#./gradlew clean build
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
./gradlew clean build -Dorg.gradle.java.home=${JAVA_HOME}
echo
