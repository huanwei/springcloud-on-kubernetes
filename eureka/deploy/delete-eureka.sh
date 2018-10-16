#!/bin/bash

kubectl delete -f configmap-peer0.yaml
kubectl delete -f configmap-peer1.yaml
kubectl delete -f configmap-peer2.yaml

kubectl delete -f deployment-peer0.yaml
kubectl delete -f deployment-peer1.yaml
kubectl delete -f deployment-peer2.yaml
