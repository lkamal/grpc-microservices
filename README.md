Microservices communication via GRPC
====================================

A sample project with services which can provide the details of `Products` along with their `Review` comments.
External APIs are using REST which internal APIs are using GRPC.

This project has two modules.
1. product-service
2. review-service-grpc

These are two separate sample microservices.

### product-service

This has REST APIs to get information on all the `Product`s, and details of each `Product` along with their `Review` comments.
````
GET /products/
````
returns all `Product`s
````
GET /products/{productId}
````
returns the specific `Product` with that id, and related `Review` comments.

Here all the `Review`s are retrieved using GRPC from `review-service-grpc` mentioned below.

### review-service-grpc

This service returns all the `Review`s of a `Product` using the id of the product.

How to build
------------

### Prerequisites

* Java version 11

* Maven version 3.6.0

Each module can be built using maven.

`mvn clean package`

How to Run
------------
Both services can be started using the jar file in `<module>/target` directory.

Product service
````
cd product-service/target

java -jar product-service-1.0-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.0)

INFO 25758 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2005 ms
INFO 25758 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 6060 (http) with context path ''
INFO 25758 --- [           main] c.d.g.j.ms.product.ProductApplication    : Started ProductApplication in 5.036 seconds (JVM running for 6.47)
````

Review Service
````
cd review-service-grpc/target

java -jar review-service-grpc-1.0-SNAPSHOT.jar

Server started, listening on 5050
`````

How to invoke the APIs
----------------------

#### Request: Get products
`GET http://localhost:6060/products` returns all the products as a JSON. This has only the `Product` details, but not about the `Review`s.

#### Response

````
[
    {
        "id": "prod-1",
        "name": "iPhone 13 Pro Max",
        "price": 1450.0,
        "reviews": null
    },
    {
        "id": "prod-3",
        "name": "Google Pixel 6",
        "price": 999.0,
        "reviews": null
    },
    {
        "id": "prod-2",
        "name": "Samsung Galaxy S22 Ultra",
        "price": 1099.0,
        "reviews": null
    }
]
````

#### Request: Get 'prod-1' product
`GET http://localhost:6060/products/prod-1` returns the details of `Product` with the id=prod-1 including `Review`s from other microservice retrieved via GRPC.

#### Response

````
{
    "id": "prod-1",
    "name": "iPhone 13 Pro Max",
    "price": 1450.0,
    "reviews": [
        {
            "id": 1,
            "title": "iPhone - Amazing",
            "message": "The best product I have ever used",
            "rating": 5
        },
        {
            "id": 2,
            "title": "iPhone - Not worth",
            "message": "Too expensive, though it is feature rich",
            "rating": 3
        },
        {
            "id": 3,
            "title": "Worst iPhone",
            "message": "Apple company's worst product, don't buy",
            "rating": 1
        }
    ]
}
````

#### Request: Get non-existing product

`GET http://localhost:6060/products/invalid-id` returns `404 NOT FOUND`

### What happens if GRPC based `Review` service is unreachable/down

#### Request: Get 'prod-1' product
`GET http://localhost:6060/products/prod-1` returns the details of `Product` with the id=prod-1, without `Review`s.

#### Response
````
{
    "id": "prod-1",
    "name": "iPhone 13 Pro Max",
    "price": 1450.0,
    "reviews": []
}
````