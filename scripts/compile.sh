#!/bin/bash

OUTPUT_DIR="../build"
SRC_DIR="../src"

mkdir -p "$OUTPUT_DIR"

javac -d "$OUTPUT_DIR" \
    --release 1.4 \
    "$SRC_DIR/myAdapter/"*.java
