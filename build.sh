#!/bin/sh
cd $CI_HOME/football-manager
./gradlew testVersions -PversionCode=$TRAVIS_BUILD_NUMBER -PversionName=0.0.$TRAVIS_BUILD_NUMBER testVersions build
