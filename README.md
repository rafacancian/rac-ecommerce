<div align="center">
  <a href="rac-ecommerce-logo.png">
    <img width="400px" src="rac-ecommerce-logo.png" alt="RAC Ecommerce Logo">
  </a>
</div>

Projeto ecommerce com diversos micro serviços de responsabilidades diferentes simulando o fluxo
de compra de um sistema de comercio eletronico

Os microservices são dividios em modulos sendo um deles o modulo common que possui as implementações
genericas de kafa para produzir e consumir mensagerias

### Microserviços

#### 1 - Common kafka

modulo generico do sistema responsavel por produzir e consumir mensagerias com kafka

#### 2 - Aplication manager

http service responsavel por iniciar os processo de mensageria.
```Default Port: 8090 ```

#### 3 - Service order

Modulo responsavel por iniciar um fluxo de ordem de compra
```Default Port: 8091```

#### 4 - service user

Modulo responsavel por armazenar os usuarios do sistema em uma base de dados, atualmente usando mock dos dados
```Default Port: 8095```

#### 5 - Service fraud detector

Modulo responsavel por validar as informações de uma ordem e verificar se a compra pode ser realizada pelo usuario
```Default Port: 8094```

#### 6 - Service email

Modulo responsavel por enviar um email sobre o processo da ordem de compra
```Default Port: 8093```

#### 7 - Service logger

Modulo responsavel por centralizar os logs de todos os microserviçoes
```Default Port: 8092```
---

## System Technologies

- [Java]: Java 12
- [Spring Boot]: Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you
  can "just run"
- [Spring Cloud]: Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed
  systems
    - [Feign]: for external server communications
- [Kafka]: Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for
  high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.
- [Zookeeper]: centralized service for maintaining configuration information, naming, providing distributed
  synchronization, and providing group services
- [Kafdrop]: Kafdrop is a web UI for viewing Kafka topics and browsing consumer groups. The tool displays information
  such as brokers, topics, partitions, consumers, and lets you view messages.
- [Lombok]: Annotations helper library
- [H2]: A very fast database embedded for tests
- [Swagger]: Swagger open source and pro tools have helped millions of API developers, teams, and organizations deliver
  great APIs.
- [Docker]: The most famous container engine used to execute the B3Invest project with their external dependencies

---

## Architecture Design

// TODO

---

## Steps to run

1. Run Kafka server
    2. The docker compose contains the kafka and Zookeeper server.
       ```docker-compose up -d```
2. Run service-order
3. Run service-user
4. Run service logger
5. Run service-email
6. Run service-fraud-detector
7. Run service-reporting

---

## Docker Compose

Kafka e Zookeeper

```
docker-compose up -d
docker logs -f <container-id>
docker run -p <external-port>:<internal-port> --name <container-name> --network <network-name> <image-name:tag>
docker-compose down
```

---

## Postman Collection

Postman collection para realizar testes <br>
[Postman collection][postman-file]

## Kafdrop

Dashboard for viewing Kafka topics and browsing consumer groups. <br>
http://localhost:9000

## Swagger

The generated swagger html page is available in the following address<br>
http://localhost:8080/swagger-ui/index.html

### Links de referencia

1. https://itnext.io/how-to-install-kafka-using-docker-a2b7c746cbdc
2. https://medium.com/better-programming/a-simple-apache-kafka-cluster-with-docker-kafdrop-and-python-cf45ab99e2b9

[Java]: https://www.java.com/pt-BR/

[spring boot]: https://spring.io/projects/spring-boot

[spring cloud]: https://spring.io/projects/spring-cloud

[feign]: https://github.com/OpenFeign/feign

[kafka]: https://kafka.apache.org/

[Zookeeper]: https://zookeeper.apache.org/

[Kafdrop]: https://github.com/obsidiandynamics/kafdrop

[lombok]: https://github.com/rzwitserloot/lombok

[h2]: http://h2database.com/html/main.html

[Swagger]: https://swagger.io/

[docker]: https://www.docker.com/

[postman-file]: https://github.com/rafacancian/java-kafka-ecommerce/tree/main/application-manager/postman

