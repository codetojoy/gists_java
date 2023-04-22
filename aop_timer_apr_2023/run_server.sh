#!/bin/bash

set -e 

./gradlew clean compileJava
./gradlew test 
./gradlew bootRun 

