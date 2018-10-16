#!/bin/bash

kubectl replace -f configmap-peer0.yaml
kubectl replace -f configmap-peer1.yaml
kubectl replace -f configmap-peer2.yaml

kubectl replace -f deployment-peer0.yaml
kubectl replace -f deployment-peer1.yaml
kubectl replace -f deployment-peer2.yaml
