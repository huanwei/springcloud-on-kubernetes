## Install Zuul rules from DB

1. Run DB: 
```
run-db.sh
```

2. create table: 

```
resources/db/zuul_db.sql
```


## Dynamic Zuul Route APIs

### /healthz

```
huandeMacBook-Pro:deploy huan$ curl http://127.0.0.1:8080/refreshRoute
refreshRoute
```

### /refreshRoute

```
huandeMacBook-Pro:deploy huan$ curl http://127.0.0.1:8080/refreshRoute
refreshRoute
```

### /routes

```
huandeMacBook-Pro:deploy huan$ curl http://127.0.0.1:8080/routes
{"/book/**":"http://127.0.0.1:8091"}
```

### How to test 
```
huandeMacBook-Pro:deploy huan$ curl http://127.0.0.1:8080/book/list
list Spring in Action
```
