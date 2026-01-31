#!/bin/bash

./build.sh clean
./build.sh bar
java -jar ./target/sensor-app-bar.jar

