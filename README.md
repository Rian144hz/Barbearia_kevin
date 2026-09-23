# Barbearia API

Backend em Java (Spring Boot) para gerenciar uma barbearia: cadastro de clientes e barbeiros, serviços oferecidos e agendamentos, com controle automático de conflito de horário.

Projeto feito pra estudo e prática, com ajuda do meu amigo Kevin.

## Stack

- Java 21
- Spring Boot (Web + Data JPA)
- PostgreSQL
- Lombok

## Rodando localmente

1. Crie um banco PostgreSQL
2. Configure `src/main/resources/application.properties` com a URL, usuário e senha do banco
3. Rode a classe `BarbeariaApplication`

## Endpoints principais

- `GET/POST/PUT/DELETE /api/pessoas`
- `GET/POST/PUT/DELETE /api/servicos`
- `GET/POST/PATCH/DELETE /api/agendamentos`
