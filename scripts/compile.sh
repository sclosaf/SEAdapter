#!/bin/bash

OUTPUT_DIR="../build"
SRC_DIR="../src"

mkdir -p "$OUTPUT_DIR"

javac -d "$OUTPUT_DIR" \
      -source 1.3 \
      -target 1.1 \
      "$SRC_DIR/myAdapter/"*.java
