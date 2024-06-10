#!/bin/sh

echo "=== style: default, icon: default ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i default

echo "\n=== style: default, icon: poker ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i poker

echo "\n=== style: rectangle, icon: default ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i default -s rectangle

echo "\n=== style: rectangle, icon: poker ==="
java -classpath './out/' org.example.FunnyJsonExplorer -f test.json -i poker -s rectangle
