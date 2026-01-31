#!/bin/bash

./build.sh clean
./build.sh foo
java -jar ./target/sensor-app-foo.jar

