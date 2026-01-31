#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

usage() {
    echo "Usage: $0 <foo|bar|clean|encrypt>"
    echo ""
    echo "Commands:"
    echo "  foo      Build Version Foo (plain text data, no Bouncy Castle)"
    echo "  bar      Build Version Bar (encrypted data, with Bouncy Castle)"
    echo "  clean    Clean build artifacts"
    echo "  encrypt  Generate encrypted data file for Version Bar"
    exit 1
}

if [ $# -ne 1 ]; then
    usage
fi

case "$1" in
    foo)
        echo "Building Version Foo..."
        cp foo-build.sbt.template build.sbt
        mkdir -p src/main/resources/META-INF/services
        cp src/main/resources/META-INF/services-foo/* src/main/resources/META-INF/services/
        sbt clean assembly
        rm -rf src/main/resources/META-INF/services
        echo ""
        echo "Built: target/sensor-app-foo.jar"
        ;;
    bar)
        echo "Building Version Bar..."
        cp bar-build.sbt.template build.sbt
        mkdir -p src/main/resources/META-INF/services
        cp src/main/resources/META-INF/services-bar/* src/main/resources/META-INF/services/
        sbt clean assembly
        rm -rf src/main/resources/META-INF/services
        echo ""
        echo "Built: target/sensor-app-bar.jar"
        ;;
    clean)
        echo "Cleaning..."
        rm -rf target project/target project/project
        rm -rf src/main/resources/META-INF/services
        rm -f build.sbt
        echo "Clean complete."
        ;;
    encrypt)
        echo "Generating encrypted data file..."
        cp bar-build.sbt.template build.sbt
        sbt "runMain net.codetojoy.sensor.bar.EncryptionUtil src/main/resources/data/sensor_data.txt src/main/resources/data/sensor_data_encrypted.txt"
        echo "Encrypted data file created."
        ;;
    *)
        usage
        ;;
esac
