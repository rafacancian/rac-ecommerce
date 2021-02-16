# RAC Ecommerce

<p align="center">
  <a href="https://github.com/rafacancian/java-kafka-ecommerce">
    <img width="50px" src="https://raw.githubusercontent.com/rafacancian/java-kafka-ecommerce/pictures/helper/layouts/rac-ecommerce-logo.png" alt="RAC Ecommerce Logo">
  </a>
</p>

**Projeto ecommerce com diversos micro serviços de responsabilidades diferentes simulando o fluxo 
de compra de um sistema de comercio eletronico.**

**Os microservices são dividios em modulos sendo um deles o modulo common que possui as implementações
genericas de kafa para produzir e consumir mensagerias**

### Microserviços

**Common kafka**
>  modulo generico do sistema.
>  responsavel por produzir e consumir mensagerias com kafka

**Aplication manager** 
> http service responsavel por iniciar os processo de mensageria.
>```Default Port: 8090 ```

**Service order**
>    modulo responsavel por iniciar um fluxo de ordem de compra
>```Default Port: 8091```

**service user**
> modulo responsavel por armazenar os usuarios do sistema em uma base de dados
> atualmente usando mock dos dados
>```Default Port: 8095```

**Service fraud detector**
> modulo responsavel por validar as informações de uma ordem e verificar
> se a compra pode ser realizada pelo usuario 
>```Default Port: 8094```

**Service email**
> modulo responsavel por enviar um email sobre o processo da ordem de compra
>```Default Port: 8093```

**Service logger**
> modulo responsavel por centralizar os logs de todos os microserviçoes
>```Default Port: 8092```
            
---

## System Technologies
- [Java]: Java 12 
- [Spring Boot]: Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
- [Spring Cloud]: providing both authentication and authorization 
  - [Feign]: for external server communications
- [Kafka]: Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.
- [Zookeeper]: centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services
- [Lombok]: Annotations helper library
- [H2]: A very fast database embedded for tests
- [Swagger]: Swagger open source and pro tools have helped millions of API developers, teams, and organizations deliver great APIs.
- [Docker]: The most famous container engine used to execute the B3Invest project with their external dependencies

---

## Architecture Design
![](https://raw.githubusercontent.com/rafacancian/java-kafka-ecommerce/pictures/helper/architecture/architecture.png)

---

## Steps to run
1. Run Kafka server 
The docker compose contains the kafka server and Zookeeper server.
Command: ```docker-compose up -d```

2. Run others projects

---

## Docker Compose
Kafka e Zookeeper
link reference: https://itnext.io/how-to-install-kafka-using-docker-a2b7c746cbdc
```
 Coomand: 
docker-compose up -d
docker logs -f <container-id>
docker run -p <external-port>:<internal-port> --name <container-name> --network <network-name> <image-name:tag>
```

---

## Postman Collection
- [Postman collection][postman-file]

## Swagger
- The generated swagger html page is available in the following address
```
    http://localhost:8080/swagger-ui/index.html
```

---
## TODO

- :heavy_check_mark: ~~kafka common~~ 
- :ballot_box_with_check: update readme

---

[Java]: https://www.java.com/pt-BR/    
[spring boot]: https://spring.io/projects/spring-boot
[spring cloud]: https://spring.io/projects/spring-cloud
[feign]: https://github.com/OpenFeign/feign
[kafka]: https://kafka.apache.org/
[Zookeeper]: https://zookeeper.apache.org/
[lombok]: https://github.com/rzwitserloot/lombok
[h2]: http://h2database.com/html/main.html
[Swagger]: https://swagger.io/
[docker]: https://www.docker.com/
[postman-file]: https://github.com/rafacancian/java-kafka-ecommerce/tree/main/application-manager/postman

