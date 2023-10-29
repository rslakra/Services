#!/bin/bash
#Author: Rohtash Lakra
clear
echo
#./gradlew bootRun
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
./gradlew bootRun -Dorg.gradle.java.home=${JAVA_HOME}
echo
