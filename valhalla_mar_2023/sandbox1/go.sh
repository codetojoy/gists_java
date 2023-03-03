#!/bin/bash

set -e

./clean.sh
./compile.sh
./test.sh
echo "unit tests complete"
sleep 0.5

./run.sh 
echo "run complete"
sleep 0.5

echo "Ready."
