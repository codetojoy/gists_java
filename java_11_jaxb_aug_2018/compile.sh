#!/bin/bash 

./clean.sh

# javac -d build/modules \ --module-source-path src \ `find src/main/java -name "*.java"`

javac -d build/modules `find src/main/java -name "*.java"` \
--add-modules java.xml.bind

