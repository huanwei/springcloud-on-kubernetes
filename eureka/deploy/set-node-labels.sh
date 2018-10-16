#!/bin/bash

kubectl label node 10.10.102.56-build run=global-eureka0
kubectl label node 10.10.102.53-slave run=global-eureka1
kubectl label node 10.10.102.52-master run=global-eureka2