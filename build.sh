#!/bin/sh
echo 'versionCode:'$1
echo 'versionName:'$2
keystorePath=$3
keystorePassword=$4
keyAlias=$5
keyPassword=$6
cd football-manager
./gradlew -PversionCode=$1 -PversionName=$2 -PkeystorePath=$keystorePath -PkeystorePassword=$keystorePassword -PkeyAlias=$keyAlias -PkeyPassword=$keyPassword  testVersions build
