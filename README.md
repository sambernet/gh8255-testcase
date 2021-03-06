# gh8255-testcase

Test case to demonstrate a regression that leads to the problem described in spring-boot issue #8255 (see https://github.com/spring-projects/spring-boot/issues/8255).

When running the self-contained spring boot application class `ch.kwsoft.spring.boot.gh8255.Gh8255Application` and testing versus the `/health` and any other actuator endpoint (`/env` in the examples below) in spring-boot 1.5.1.RELEASE:

```
$ curl http://localhost:8080/health -u admin:secret
{"status":"UP"}

$ curl http://localhost:8080/env -u admin:secret
{"timestamp":1487293786413,"status":403,"error":"Forbidden","message":"Access is denied. User must have one of the these roles: SYSOPS","path":"/env"}
```

The regression is exhibited by changing spring-boot to version 1.4.4.RELEASE (use branch `1.4.4-working`):

```
$ curl http://localhost:8080/health -u admin:secret
{"status":"UP","diskSpace":{"status":"UP","total":225438593024,"free":54106603520,"threshold":10485760}}

$ curl http://localhost:8080/env -u admin:secret
{"profiles":[],"server.ports":{"local.server.port":8080},"servletContextInitParams":{},"systemProperties":{"java.runtime.name":"Java(TM) SE Runtime Environment",....}
```