#!/bin/bash

set -e

ROOT_DIR=$PWD
SRC_DIR=$ROOT_DIR/src/main/java
TARGET_DIR=$ROOT_DIR/my_build/main

mkdir -p $TARGET_DIR

javac --release 20 --enable-preview -XDenablePrimitiveClasses \
-d $TARGET_DIR `find $SRC_DIR -name "*.java"`

echo "Ready."
