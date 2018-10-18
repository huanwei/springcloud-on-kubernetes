#!/bin/bash

cd ../
mvn clean install -DskipTests
cd deploy
rm -rf nohup.out
nohup java -jar ../target/demo-1.0-SNAPSHOT.jar &
