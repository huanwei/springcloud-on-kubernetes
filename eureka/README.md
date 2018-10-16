## Build eureka docker images
```
cd deploy

./build-docker.sh
```

## Install eureka cluster on Kubernetes

Before setting up eureka cluster, specify 3 fixed nodes here and set labels for each node accordingly.

For example:

```
cd deploy

./set-node-labels.sh

```
And install the eureka cluster:

```
cd deploy

./install-eureka.sh
```
