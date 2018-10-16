#!/bin/bash

cd ../
mvn clean install -DskipTests
cd deploy
cp ../target/eureka-1.0-SNAPSHOT.jar .
docker build -t huanwei/springcloud-eureka:v0.1 .
rm -rf eureka-1.0-SNAPSHOT.jar
docker save -o springcloud-eureka-v0.1.tar huanwei/springcloud-eureka:v0.1