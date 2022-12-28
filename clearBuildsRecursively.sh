#!/bin/bash
#Author: Rohtash Lakra
clear
echo
#find $(pwd) -name "target" -print0 | xargs -0 rm -rf
#find $(pwd) -name "target" -print -depth -exec rm -rf {} \;
FOLDERS=("build" "out" "target")
for entry in "${FOLDERS[@]}"
do
  echo "Deleting [${entry}] folder recursively ..."
#  find $(pwd) -name "${entry}" -print0 | xargs -0 rm -rf
  find $(pwd) -name "${entry}" -print -depth -exec rm -rf {} \;
  echo
done
echo

