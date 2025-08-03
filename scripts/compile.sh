#!/bin/bash

OUTPUT_DIR="../build"
SRC_DIR="../src"

mkdir -p "$OUTPUT_DIR"

javac -d "$OUTPUT_DIR" \
      "$SRC_DIR/myAdapter/"*.java
