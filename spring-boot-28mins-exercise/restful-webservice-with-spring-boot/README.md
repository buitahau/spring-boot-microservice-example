RESTFUL Spring boot
-------------------
- HATEOAS: Provides some APIs to ease creating REST.
representations that follow the HATEOAS principle when working with Spring and especially Spring MVC. The core problem it tries to address is link creation and representation assembly.
- Swagger2: Auto builds reference documentation every change in the API
(/v2/api-docs, /swagger-resources).
- spring-boot-starter-actuator: Includes a number of additional features to
help you monitor and manage your application when you push it to production.
Auditing, health, and metrics gathering can also be automatically applied to
your application (/actuator).
- @JsonIgnore, @JsonIgnoreProperties: Use to filter the properties of the
bean when serializing and de-serializing the JSON data. That fields will
never come in response.
- MappingJacksonValue: Dynamic filtering the bean's properties depend on
request.

-------------------
Run spring boot project with command
Go to project directory
Run:
 - Default configuration : `mvn spring-boot:run`
 - With arguments: `mvn spring-boot:run -Dspring-boot.run.arguments=.....`

-------------------
- Zuul: Zuul is the front door for all requests from devices and web sites to the backend of the Netflix streaming application. As an edge service application, Zuul is built to enable dynamic routing, monitoring, resiliency and security. It also has the ability to route requests to multiple Amazon Auto Scaling Groups as appropriate.

-------------------
- H2 database: an open-source lightweight Java database. It can be embedded
in Java applications or run in the client-server mode. Mainly, H2 database
can be configured to run as inmemory database, which means that data will not
 persist on the disk. Because of embedded database it is not used for
 production development, but mostly used for development and testing. To go
 to h2 administrator, hit "/h2-console" with username is "sa" and password is
 empty.
