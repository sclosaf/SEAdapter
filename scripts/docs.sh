#!/bin/bash

SRC_DIR="../src"
DOC_DIR="../doc"

mkdir -p "$DOC_DIR"

javadoc -d "$DOC_DIR" \
        -sourcepath "$SRC_DIR" \
        -subpackages myAdapter myTest myException\
        -classpath "../lib/junit-4.13.2.jar"
