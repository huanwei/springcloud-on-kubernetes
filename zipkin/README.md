## Install zipkin on Kubernetes

Before setting up eureka cluster, specify 1 fixed node here and set label accordingly.

For example:

```
cd deploy

./set-node-labels.sh

```
And install the zipkin:

```
cd deploy

./install-zipkin.sh
```
