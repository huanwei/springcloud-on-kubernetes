## Before add Hystrix

Run service provider `bookstore` , run service consumer `reading`:

```
huandeMacBook-Pro:reading huan$ curl 127.0.0.1:8080/to-read
Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)
```


## Verify circuit breaker after adding Hystrix

Stop service provider `bookstore`, visit service consumer `reading` again:

```
huandeMacBook-Pro:reading huan$ curl 127.0.0.1:8080/to-read
Cloud Native Java (O'Reilly)
```


