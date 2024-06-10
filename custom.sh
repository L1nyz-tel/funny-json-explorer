#!/bin/sh

echo "=== style: default, icon: custom ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i custom

echo "\n=== style: rectangle, icon: custom ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i custom -s rectangle