#!/bin/bash

set -e

rm -f *.class
javac *.java

# security manager with policy 
java "-Djava.security.manager –Djava.security.policy=./java.policy"  Runner 

# security manager, but no policy 
# java "-Djava.security.manager"  Runner 

# no security manager
# java Runner
