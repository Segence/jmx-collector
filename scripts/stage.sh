#!/usr/bin/env bash

set -e

function usage {
    echo "Usage: $0 <dependencies|unit-test|publish-local|publish|publish-to-bintray> [PROJECT]"
    exit 1
}

function dependencies {
    ./gradlew clean dependencies
}

function unit-test {
    ./gradlew clean test jacocoTestReport
}

function publish-local {
    ./gradlew clean test publishToMavenLocal
}

function publish {
    ./gradlew clean test publish
}

function publish-to-bintray {
    ./gradlew clean test bintrayUpload
}

CURRENT_DIR=`dirname "$0"`
cd $CURRENT_DIR/..

GOAL=$1

case $GOAL in
  dependencies)
    dependencies
    ;;
  unit-test)
    unit-test
    ;;
  publish-local)
    publish-local
    ;;
  publish)
    publish
    ;;
  publish-to-bintray)
    publish-to-bintray
    ;;
  *)
    usage
    ;;
esac
