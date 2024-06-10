#!/bin/sh

mkdir out
javac -d ./out/ -classpath './src/main/java/' ./src/main/java/org/example/*.java
