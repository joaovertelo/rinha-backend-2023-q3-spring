# rinha-backend-2023-spring

![](./rinha-backend.png)

Repositório do desafio [Rinha de Backend 2023 Q3](https://github.com/zanfranceschi/rinha-de-backend-2023-q3)

Fiz um vídeo falando sobre as minhas implementações para o desafio Rinha de Backend e você pode assisti-lo a partir
desse link [O JAVA TANKOU O RINHA DE BACKEND?
](https://www.youtube.com/watch?v=2M-mYZD05S0)

Nesse repositório encontra-se as branchs com as implementações de Spring.

- ``main``: Contém a implementação com melhor desempenho
- ``v1-sync``: Implementação com inserção de forma sincrona com cache.
- ``v1-sem-cache``: Implementação com inserção de forma sincrona sem cache.
- ``v2-rinha``: Implementação de com inserção de sicrona e cache com redis.
- ``v2-webflux``: versão utilziando spring webflux


** Realizei a implementação utilizando o framework Micronaut e se encontra no nesse link [rinha-backend-2023-q3-micronaut](https://github.com/joaovertelo/rinha-backend-2023-q3-micronaut) 

## Requisitos para execução

Para rodar, precisamos ter instalado:

* Docker
* Gatling ([Vídeo ensinando utilizar o Gatling](https://youtu.be/3DHJhAgAwPg?si=xVA-wxd34xvkt7v4))

## Como executar?

Para executar em seu ambinte local você pode executar o seguinte comando:

1. ``docker compose docker-compose-local.yml up``
2. ``mvn spring-boot:run`` ou executar a classe ``Application`` a partir de sua IDE preferida. 

Para executar o docker compose com nginx e as 2 API's você pode executar somente o comando:

* ``docker compose up``

Para terminar a execução dos containers, execute o comando:

* ``docker compose down``

## Para executar os testes

Execute o comando para executar teste no: 

#### Linux e Mac:

``  
cd stress-test
./run-test.sh
``

#### Windows:
``  
cd stress-test
./run-test.ps1
``