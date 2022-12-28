#/bin/bash
#Author: Rohtash  Lakra
#
# Run Node.js Application
clear
#rm -rf node_modules
buildFilePath=""
outFilePath=""
targetFilePath=""
for file in */ ; do
  if [[ -d "$file" && ! -L "$file" && ! "$file" == "gradle/" ]]; then
    buildFilePath="${file}build"
    outFilePath="${file}out"
    targetFilePath="${file}target"
    echo "Removing ${buildFilePath}"
    rm -rf $buildFilePath
    echo "Removing ${outFilePath}"
    rm -rf $outFilePath
    echo "Removing ${targetFilePath}"
    rm -rf $targetFilePath
  fi
done
echo
