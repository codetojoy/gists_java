#!/bin/bash

rm -rf my_build

javac --release 19 --enable-preview -d my_build `find src/main/java -name *.java`

java --enable-preview --class-path my_build Runner 

echo "Ready."
