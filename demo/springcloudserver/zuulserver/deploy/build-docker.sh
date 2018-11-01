#!/bin/bash

cd ../
mvn clean install -DskipTests
cd deploy
cp ../target/zuul-1.0-SNAPSHOT.jar .
docker build -t huanwei/springcloud-zuul:v0.1 .
rm -rf zuul-1.0-SNAPSHOT.jar
docker save -o springcloud-zuul-v0.1.tar huanwei/springcloud-zuul:v0.1