#!/bin/bash

OUTPUT_DIR="../build"
SRC_DIR="../src"
LIB_DIR="../lib"
MATCHER_DIR="../matcher"

./compile.sh || exit 1

javac -d "$OUTPUT_DIR" \
      -cp "$OUTPUT_DIR:$LIB_DIR/junit-4.13.2.jar:$MATCHER_DIR/hamcrest-core-1.3.jar" \
      "$SRC_DIR/myTest/"*.java || exit 1

java -cp "$OUTPUT_DIR:$LIB_DIR/junit-4.13.2.jar:$MATCHER_DIR/hamcrest-core-1.3.jar" \
     org.junit.runner.JUnitCore myTest.TestRunner
