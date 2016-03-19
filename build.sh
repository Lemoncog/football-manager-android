#!/bin/sh
cd $CI_HOME/football-manager
./gradlew testVersions build --stacktrace
