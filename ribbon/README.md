## How to verify Ribbon

Run service provider `sayhello` , run service consumer `user`:

```
huandeMacBook-Pro:user huan$ curl http://localhost:8888/hi
Salutations, Artaban!

huandeMacBook-Pro:user huan$ curl http://localhost:8888/hi?name=orontes
Hi there, orontes!

```

### Add load balance across server instances with Ribbon

Run many service provider instances  of `sayhello`

```
huandeMacBook-Pro:user huan$ curl http://localhost:8888/hi
Greetings, Artaban!

```