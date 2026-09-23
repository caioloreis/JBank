# 🏦 JBank

API bancária desenvolvida em **Java com Spring Boot**, criada como projeto de estudo e portfólio durante meu aprendizado de desenvolvimento backend.

O objetivo do projeto é colocar em prática conceitos de **Java, Programação Orientada a Objetos, Spring Boot, APIs REST, JPA e banco de dados**.

##  Tecnologias

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Docker

##  Objetivo

O JBank foi desenvolvido para praticar conceitos de desenvolvimento backend utilizando o ecossistema Java e Spring Boot.

Durante o desenvolvimento, estou colocando em prática e estudando conceitos como:

* Programação Orientada a Objetos
* Criação de APIs REST
* Controllers, Services e Repositories
* Persistência de dados com JPA/Hibernate
* Integração com MySQL
* Validação de dados
* Tratamento de exceções
* Organização de um projeto Spring Boot

## 📚 Conceitos em estudo

Conforme avanço nos estudos de Spring Boot, pretendo aplicar novos conceitos ao projeto, incluindo:

* Filters e Interceptors para manipulação e auditoria de requests e responses.
* Arquitetura do Spring Web e criação de Handlers personalizados.
* Validações avançadas com Hibernate Validator e tratamento de exceções.
* Técnicas para garantir a integridade das transações e controle de concorrência.
* Consultas avançadas com JPA Projections para otimização de queries complexas.

##  Estrutura do projeto

```text
src
└── main
    ├── java
    │   └── ...
    │
    └── resources
        └── application.properties
```

## ⚙️ Como executar

### Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java 21
* Maven
* MySQL
* Docker (opcional)

### Clone o repositório

```bash
git clone https://github.com/caioloreis/JBank.git
```

### Entre na pasta

```bash
cd JBank
```

### Execute o projeto

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

## 🗄️ Banco de dados

O projeto utiliza **MySQL** para persistência dos dados.

As configurações de conexão podem ser definidas no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jbank
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

> ⚠️ Não coloque senhas ou informações sensíveis diretamente no repositório público.

## 🚧 Status do projeto

**Em desenvolvimento**

Este projeto faz parte do meu processo de aprendizado em **Java e Spring Boot** e continuará recebendo novas funcionalidades e melhorias conforme avanço nos estudos.

## 👨‍💻 Sobre mim

Sou estudante de **Análise e Desenvolvimento de Sistemas**, atualmente focado em **Java e desenvolvimento backend**.

Estou utilizando projetos como o JBank para colocar em prática os conhecimentos adquiridos durante meus estudos e construir meu portfólio na área de desenvolvimento de software.

---

⭐ Se este projeto foi útil para você, considere deixar uma estrela no repositório!
