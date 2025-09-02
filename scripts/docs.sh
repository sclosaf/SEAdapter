#!/bin/bash

SRC_DIR="../src"
DOC_DIR="../doc"
LIB_DIR="../lib"
MATCHER_DIR="../Matcher"

mkdir -p "$DOC_DIR"

javadoc -d "$DOC_DIR" \
        -sourcepath "$SRC_DIR" \
        -subpackages myAdapter myTest myException \
        -classpath "$LIB_DIR/junit-4.13.jar:$MATCHER_DIR/hamcrest-core-1.3.jar"
