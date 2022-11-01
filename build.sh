#!/bin/bash
#~/jdkSwitch 11
export JAVA_HOME="/Library/Java/JavaVirtualMachines/openjdk-11.jdk/Contents/Home"
echo "${JAVA_HOME}"
#mvn -X clean install
mvn clean install

