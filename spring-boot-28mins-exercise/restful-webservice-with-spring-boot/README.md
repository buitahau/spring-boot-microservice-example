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

-------------------
Centralize Configuration.

It is used for gather the configuration in one place. This will help the
developer control the configuration in all services. The value of
configuration in the remote repository has a higher priority than in the local
service.

We use the `spring-boot-starter-actuator` and the
`spring-cloud-starter-bus-amqp` dependency to broadcast the changes
configuration for all services without restarting them.
Some useful urls:
- POST /actuator/bus-refresh
- localhost:15672 with guest/guest
- localhost:8012/AppConfigServer/default
- localhost:8012/users-us/default


Errors: https://www.programmersought.com/article/99757216145/

-------------------
Spring Boot Actuator

Provide useful features to manage and monitor spring boot application data
analysis information.

Some useful features:
- /health - Health check
- /beans - Displays a complete list of Beans in your Microservice
- /httptrace - Displays HTTP trace information (by default, the last 100 HTTP
 request-response exchanges)
- And more at https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html

-------------------
Encryption and Decryption of Configuration Properties

Keyword: download java jce (Java cryptography extension)

2 useful methods:
- POST /encrypt
- POST /decrypt

We use the `{cipher}` prefix to mark that the value is encrypted.

-------------------
Communication between microservices

Communication Types
- Synchronous HTTP communication. The sender micro service has to wait until
it receives a response and it is also a one to one way of communication.
- Asynchronous communication over AMQP (Advanced message queuing protocol).
The sender micro service does not wait until the message is processed. The
micro service which is called consumer consumes this message from a queue.
This way there can be event multiple consumers of single message and if a
task to process the message is very time and resource consuming like for
example image or video download and processing then send their micro servers
does not need to wait until this very heavy task is performed.

2 ways to communicate between micro services.
* `RestTemplate`: use HTTP client which allows your code to send HTTP Request
 and also handle HTTP Response (https://www.appsdeveloperblog
 .com/spring-resttemplate-tutorial/).

The useful method:

  `public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables)`

* `Feign`: is an HTTP Client which helps spring boot application send HTTP
request to a remote or an internal micro service and get back the response.
Beside that, it is declarative http client, so developer can be easy to read
and maintain code later. Last, Feign support the client - side load balancing.

Some useful steps:

    @EnableFeignClients

    @FeignClient(name="target-ws"): add on interface which will call to target service.

    Logger.level.FULL: Show logs when using feign to make it easy for developers debugging.

    ErrorDecoder: it is using to get useful information for example http
    response, status code, body of response, response's header... We can base
     on that error to custom the exception. Beside that, we have a central
     place to handle feign errors. The `decode` method will receive all
     errors from feign.

    @EnableCircuitBreaker: Use to call the fallback method when the target
    micro service is down. Need to enable hytrix (feign.hystrix.enabled=true)
     and add fall back property to feign client.

    FallBackFactory: Use to central the fall method when the target micro
    service is down. It helps to show the log, help the developer
    know the target micro service is down.
