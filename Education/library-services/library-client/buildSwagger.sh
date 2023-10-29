#!/bin/bash
#Author:Rohtash Lakra
echo
mvn clean compile -DskipTests=true -Dfindbugs.skip=true -DfailIfNoTests=false -Dcheckstyle.skip -DskipSwagger=false
echo


