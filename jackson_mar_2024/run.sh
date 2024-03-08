#!/bin/bash

set -e

./gradlew run --args=$PWD/$1
