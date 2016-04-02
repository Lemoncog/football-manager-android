#!/bin/sh
echo 'versionCode:'$1
echo 'versionName:'$2
cd football-manager
./gradlew -PversionCode=$1 -PversionName=$2 testVersions build
