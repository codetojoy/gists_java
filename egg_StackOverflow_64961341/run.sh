#!/bin/bash

set -e 

rm -f *.class

javac Example.java

java Example 
