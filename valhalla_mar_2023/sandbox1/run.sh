#!/bin/bash

set -e

ROOT_DIR=$PWD
SRC_DIR=$ROOT_DIR/src/main/java
TARGET_DIR=$ROOT_DIR/my_build/main
CLASSPATH=$CLASSPATH:$TARGET_DIR

# echo $CLASSPATH
java --enable-preview -XX:+EnableValhalla -XX:+EnablePrimitiveClasses \
-cp $CLASSPATH \
net.codetojoy.Runner

echo "Ready."
