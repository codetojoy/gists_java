#!/bin/bash

set -e 

export CLASSPATH=.:$PWD/lib/abc

echo "TRACER CLASSPATH:"
echo $CLASSPATH
echo ""
echo ""

echo "TRACER running Quick:"
javac Quick.java
java Quick
