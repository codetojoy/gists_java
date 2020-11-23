#!/bin/bash

set -e

export CLASSPATH=.:./lib/commons-lang3-3.11.jar
javac Combine.java

java Combine 
