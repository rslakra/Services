#!/bin/bash
#Author:Rohtash Lakra
clear
# Step 1
git submodule update --init --recursive
# Step 2
git pull --recurse-submodules
echo
# check-out master branch to avoid checkout of #head.
PROJECT_HOME=$PWD
echo
echo "Syncing [${PROJECT_HOME}] ..."
echo
SUB_MODULES=("Automobiles" "Business" "Commerce" "Commodity" "Education" "Healthcare" "Manufacturing" "Monitoring" "Multimedia" "Samples" "SpringSecurity" "Technology" "Transportation")
echo "SubModules: ${SUB_MODULES}"
# get length of an array
totalSubModules=${#SUB_MODULES[@]}
echo "totalSubModules: ${totalSubModules}"
echo
# use for loop to read all values and indexes
for (( i=1; i<${totalSubModules}+1; i++ ));
do
  entry=${SUB_MODULES[$i-1]}
  echo "Syncing [${i} / ${totalSubModules}] : [${entry}] ..."
  cd ${entry}
  git checkout master
  cd ..
  echo
done
echo
