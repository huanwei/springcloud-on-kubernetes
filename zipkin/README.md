## Install standalone zipkin server on Kubernetes

Before setting up zipkin server, specify 1 fixed node here and set label accordingly.

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

## Install zipkin server for large scale

check https://github.com/openzipkin/zipkin/tree/master/zipkin-server
