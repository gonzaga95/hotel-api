# Hotel API - Reserva de Hotel - Santander Coders 2024

API RESTful para gerenciamento de reservas de hotel, desenvolvida em Java com Spring Boot. Esta aplicação permite criar, atualizar, listar e excluir reservas de hotel, além de incluir autenticação básica para segurança.

## Índice

1. [Pré-requisitos](#pré-requisitos)
2. [Configuração](#configuração)
3. [Estrutura do Projeto](#estrutura-do-projeto)
4. [Endpoints](#endpoints)
5. [Segurança](#segurança)
6. [Configurações de Banco de Dados](#configurações-de-banco-de-dados)
7. [Swagger](#swagger)

## Pré-requisitos

- **Java 21** ou superior
- **Maven** para gerenciar dependências e construir o projeto

## Configuração

Clone o repositório e faça o build da aplicação usando o Maven:
```bash
git clone https://github.com/mauriciogonrails/hotel-api.git
cd projeto-hotel-api
mvn clean install
```

Para executar:
```bash
mvn spring-boot:run
```

## Estrutura do Projeto

- **Controller**: Classe `ReservaController`, que define os endpoints de CRUD para reservas.
- **Service**: Classe `ReservaService`, responsável pela lógica de negócio, como atualizar e listar reservas, além de aplicar regras específicas, como limitar campos para atualização.
- **Model**: Classe `ReservaHotel`, representando a entidade de reserva no banco de dados.
- **DTOs**:
    - `ReservaDTO`: usado para representar dados da reserva para criação, listagem e detalhes.
    - `ReservaUpdateDTO`: usado exclusivamente para atualizar as datas da reserva, limitando o acesso a outros campos.
- **Configuração de Segurança**: A classe `SecurityConfiguration` define a autenticação básica (basic-auth) e permite acesso público ao Swagger e ao H2 Console.

## Endpoints

### ReservaController

| Método | Endpoint                | Descrição                                               |
|--------|--------------------------|---------------------------------------------------------|
| `POST` | `/reservas`              | Cria uma nova reserva                                   |
| `GET`  | `/reservas/all`          | Lista todas as reservas                                 |
| `GET`  | `/reservas/uf/{uf}`      | Lista reservas por estado (UF)                          |
| `GET`  | `/reservas/diarias-longas` | Lista reservas com as diárias mais longas               |
| `PUT`  | `/reservas/{numeroReserva}` | Atualiza datas da reserva (início e fim)               |
| `DELETE` | `/reservas/{numeroReserva}` | Exclui uma reserva com base no número da reserva     |

> **Nota**: Apenas os campos `dataInicioReserva` e `dataFimReserva` podem ser atualizados através do método `PUT`, como definido pelo `ReservaUpdateDTO`.

## Segurança

A aplicação usa Spring Security com autenticação básica (`basic-auth`). Um usuário padrão é configurado em memória:

- **Usuário**: `javaweb`
- **Senha**: `teste123`

Para acessar endpoints protegidos, inclua essas credenciais no cabeçalho de autorização.

## Configurações de Banco de Dados

A aplicação usa um banco de dados H2 em memória, acessível no console H2 em `http://localhost:8080/h2-console`.

Configurações em `application.properties`:
```properties
# Informações gerais da aplicação
spring.application.name=hotel-api
server.port=8090

# Configuração do Swagger OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# Configurações do Banco de Dados H2
spring.datasource.url=jdbc:h2:mem:hotelapi
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=teste123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true

# Console do H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false
```

## Swagger

A documentação dos endpoints da API está disponível via Swagger em `http://localhost:8080/swagger-ui.html`. No Swagger UI, você pode explorar todos os endpoints e fazer requisições, incluindo o método `PUT` para atualização de reservas com apenas os campos permitidos (`dataInicioReserva` e `dataFimReserva`).


## Projeto

Esse projeto foi desenvolvido para a disciplina de Java Web da plataforma Ada Tech, no programa Santander Coders 2024.
