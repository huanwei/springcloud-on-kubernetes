#!/bin/bash

kubectl create -f configmap-peer0.yaml
kubectl create -f configmap-peer1.yaml
kubectl create -f configmap-peer2.yaml

kubectl create -f deployment-peer0.yaml
kubectl create -f deployment-peer1.yaml
kubectl create -f deployment-peer2.yaml
